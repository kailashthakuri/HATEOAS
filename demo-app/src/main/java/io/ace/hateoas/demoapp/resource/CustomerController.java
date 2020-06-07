package io.ace.hateoas.demoapp.resource;

import io.ace.hateoas.demoapp.entity.Customer;
import io.ace.hateoas.demoapp.entity.Order;
import io.ace.hateoas.demoapp.service.CustomerService;
import io.ace.hateoas.demoapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public CollectionModel<Customer> getAllCustomers() {
        List<Customer> customers = customerService.getCustomers();
        customers.forEach(customer -> {
            Link selfLink = linkTo(CustomerController.class).slash(customer.getId()).withSelfRel();
            customer.add(selfLink);
            if (orderService.getOrderByCustomer(customer.getId()).size() > 0) {
                Link ordersLink = linkTo(methodOn(CustomerController.class).getOrderByCustomer(customer.getId())).withRel(LinkRelation.of("allOrders"));
                customer.add(ordersLink);
//                try {
//                    Link test = linkTo(CustomerController.class.getMethod("getOrderByCustomer", Long.class), customer.getId()).withSelfRel();
//                } catch (NoSuchMethodException e) {
//                    e.printStackTrace();
//                }
            }
        });
        CollectionModel collectionModel = CollectionModel.of(customers, linkTo(CustomerController.class).withSelfRel());
        return collectionModel;
    }

    @RequestMapping(value = "/{id}/orders", produces = "application/hal+json")
    public CollectionModel<Order> getOrderByCustomer(@PathVariable Long customerId) {
        List<Order> orderByCustomer = this.orderService.getOrderByCustomer(customerId);
        orderByCustomer.forEach(order -> {
            order.add(linkTo(methodOn(OrderController.class).getOrderById(order.getId())).withSelfRel());
        });
        Link selfLink = linkTo(methodOn(CustomerController.class).getOrderByCustomer(customerId)).withSelfRel();
        CollectionModel collectionModel = CollectionModel.of(orderByCustomer, selfLink);
        return collectionModel;
    }


    @GetMapping("/{id}")
    public EntityModel<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        customer.add(Link.of("http://localhost:8080/customer/" + customer.getId()));
        return EntityModel.of(customer);
    }

}
