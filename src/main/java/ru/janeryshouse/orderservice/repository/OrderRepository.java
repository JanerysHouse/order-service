package ru.janeryshouse.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.janeryshouse.orderservice.model.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}