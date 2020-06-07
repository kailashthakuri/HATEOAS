package io.ace.hateoas.demoapp.service;

import io.ace.hateoas.demoapp.entity.Order;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    List<Order> orderList = Arrays.asList(
            new Order(1L, 1L, "Soap", 12.0),
            new Order(2L, 1L, "Bag", 2.0),
            new Order(3L, 2L, "Bottle", 120.0),
            new Order(4L, 2L, "Laptop", 1.0));

    public List<Order> getOrders() {
        return orderList;
    }

    public Optional<Order> getOrderById(Long id) {
        return orderList.stream()
                .filter(order -> order.getId() == id)
                .findFirst();
    }

    public List<Order> getOrderByCustomer(Long customerId) {
        return orderList.stream()
                .filter(order -> order.getCustomerId() == customerId)
                .collect(Collectors.toList());
    }

    public boolean delete(Long id) {
        if (orderList.removeIf(order -> order.getId() == id)) {
            return true;
        }
        return false;
    }

}
