package io.ace.hateoas.demoapp.entity;

import org.springframework.hateoas.RepresentationModel;

public class Order extends RepresentationModel<Order> {
    private Long id;
    private String name;
    private Long customerId;
    private Double quantity;

    public Order(Long id, Long customerId, String name, Double quantity) {
        this.id = id;
        this.customerId = customerId;
        this.name = name;
        this.quantity = quantity;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
