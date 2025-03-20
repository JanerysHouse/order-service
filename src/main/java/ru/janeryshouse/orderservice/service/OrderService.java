package ru.janeryshouse.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.janeryshouse.orderservice.client.InventoryClient;
import ru.janeryshouse.orderservice.dto.OrderRequest;
import ru.janeryshouse.orderservice.dto.OrderResponse;
import ru.janeryshouse.orderservice.mapper.OrderMapper;
import ru.janeryshouse.orderservice.model.Order;
import ru.janeryshouse.orderservice.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryClient inventoryClient;


    public List<OrderResponse> getAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    public Order findById(UUID id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Order not found"));
    }

    public OrderResponse create(OrderRequest request) {
        return Optional.of(request)
                .filter(req -> inventoryClient.isInStock(req.skuCode(), req.quantity()))
                .map(orderMapper::toEntity)
                .map(orderRepository::save)
                .map(orderMapper::toOrderResponse)
                .map(savedEntity -> {
                    log.info("Продукт создан: {}", savedEntity);
                    return savedEntity;
                })
                .orElseThrow(() -> new RuntimeException("Product not stock"));

    }

    public OrderResponse update(OrderRequest request) {
        var entity = orderRepository.findById(request.id()).orElseThrow(
                () -> new RuntimeException("Order not found"));
        log.info("Продукт по такому id, не был найден: {}", request.id());
        entity.setOrderNumber(request.orderNumber());
        entity.setPrice(request.price());
        entity.setQuantity(request.quantity());
        entity.setSkuCode(request.skuCode());

        return orderMapper.toOrderResponse(orderRepository.save(entity));
    }

    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }

}


