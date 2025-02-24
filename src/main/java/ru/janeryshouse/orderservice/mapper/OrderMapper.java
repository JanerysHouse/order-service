package ru.janeryshouse.orderservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.janeryshouse.orderservice.model.Order;
import ru.janeryshouse.orderservice.model.OrderRequst;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    Order toEntity(OrderRequst orderRequst);

    OrderRequst toOrderRequst(Order order);
}