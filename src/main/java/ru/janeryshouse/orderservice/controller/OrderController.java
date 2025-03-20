package ru.janeryshouse.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.janeryshouse.orderservice.dto.OrderRequest;
import ru.janeryshouse.orderservice.dto.OrderResponse;
import ru.janeryshouse.orderservice.model.Order;
import ru.janeryshouse.orderservice.service.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.findById(id));
    }


    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.create(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderResponse> update(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.update(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
