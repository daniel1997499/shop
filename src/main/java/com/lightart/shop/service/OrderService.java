package com.lightart.shop.service;

import com.lightart.shop.model.OrderItem;
import com.lightart.shop.repository.OrderRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<OrderItem> getAllOrders() {
        return orderRepository.findAll();
    }

    public void createNewOrder(OrderItem order) throws NullPointerException {
        if (order.getName() != null && order.getAddress() != null && order.getPhone() != null && order.getEmail() != null && order.getItem() != null && order.getCount() != null)
            orderRepository.save(order);
        else
            throw new NullPointerException("Null value found, expected not null");
    }

    public void deleteOrder(Long id) {
        if (orderRepository.findById(id).isPresent())
            orderRepository.deleteById(id);
        else
            throw new ObjectNotFoundException("no object with id: " + id, "null name");
    }
}
