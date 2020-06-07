package io.ace.hateoas.demoapp.entity;

import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class Customer extends RepresentationModel<Customer> {
    private Long id;
    private String name, address;
    private String companyName;
    private List<Order> orderList = new ArrayList<>();

    public Customer(Long id, String name, String address, String companyName) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.companyName = companyName;
    }

    public Customer(Long id, String name, String address, String companyName, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.companyName = companyName;
        this.orderList = orders;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
