package io.ace.hateoas.demoapp.resource;

import io.ace.hateoas.demoapp.entity.Order;
import io.ace.hateoas.demoapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/{id}")
    public EntityModel<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> orderById = orderService.getOrderById(id);
        Order order = orderById.orElse(null);
        order.add(WebMvcLinkBuilder.linkTo(OrderController.class).slash(order.getId()).withSelfRel());
//        order.add(WebMvcLinkBuilder.linkTo(OrderController.class).slash(order.getId()).slash("/delete").withRel("delete"));
        order.add(WebMvcLinkBuilder.linkTo(OrderController.class).slash(order.getId()).slash
                ("/edit").withRel("edit"));
        return EntityModel.of(order);
    }


    @GetMapping
    public CollectionModel<Order> getOrders() throws Exception {
        List<Order> orders = orderService.getOrders();
        Method getOrderByIdMethod = OrderController.class.getMethod("getOrderById", Long.class);
        Method deleteMethod = OrderController.class.getMethod("delete", Long.class);
        orders.forEach(order -> {
            LinkRelation deleteRel = LinkRelation.of("delete");
            Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).delete(order.getId())).withRel(deleteRel);
            order.add(WebMvcLinkBuilder.linkTo(getOrderByIdMethod, order.getId()).withSelfRel());
            order.add(deleteLink);
//          order.add(WebMvcLinkBuilder.linkTo(deleteMethod, order.getId()).withRel("delete"));
        });
        CollectionModel collectionModel =
                CollectionModel.of(orders, WebMvcLinkBuilder.linkTo(OrderController.class).withSelfRel());
        return collectionModel;
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity delete(@PathVariable Long id) {
        if (this.orderService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
