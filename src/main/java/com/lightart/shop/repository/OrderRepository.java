package com.lightart.shop.repository;

import com.lightart.shop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findOrderById(Long id);
    List<OrderItem> findOrderByName(String name);
    List<OrderItem> findOrderByAddress(String address);
    List<OrderItem> findOrderByPhone(String phoneNumber);
    List<OrderItem> findOrderByEmail(String email);
    List<OrderItem> findOrderByItem(String item);
    List<OrderItem> findOrderByCount(Long count);
    List<OrderItem> findOrderByCreated(Long count);
}