package ru.janeryshouse.orderservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.janeryshouse.orderservice.dto.OrderRequest;
import ru.janeryshouse.orderservice.dto.OrderResponse;
import ru.janeryshouse.orderservice.event.OrderPlacedEvent;
import ru.janeryshouse.orderservice.model.Order;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    Order toEntity(OrderRequest orderRequst);

    OrderRequest toOrderRequst(Order order);

    Order toEntity(OrderResponse orderResponse);

    OrderResponse toOrderResponse(Order order);

    @Mapping(source = "orderNumber", target = "orderNumber")
    @Mapping(source = "userDetails.email", target = "email")
    OrderPlacedEvent toOrderPlacedEvent(Order order, OrderRequest request);
}