package io.ace.hateoas.demoapp.service;


import io.ace.hateoas.demoapp.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService {
    List<Customer> customers = Arrays.asList(
            new Customer(1L, "kailash", "ktm", "Cotiviti"),
            new Customer(2L, "dipen", "lalitpur", "Verscend"));

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomerById(Long id) {
        Customer customer = null;
        for (Customer customer1 : customers) {
            if (id == customer1.getId()) {
                customer = customer1;
                break;
            }
        }
        return customer;
    }
}
