package io.ace.hateoas.demoapp.client;


import io.ace.hateoas.demoapp.entity.Order;
import io.ace.hateoas.demoapp.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Hop;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.server.core.TypeReferences;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/client/traverson")
public class TraversonClient {

    Logger logger = LoggerFactory.getLogger(TraversonClient.class);

    @RequestMapping("/persons")
    public ResponseEntity getAllPersonsLinks() {
        Traverson traverson = new Traverson(URI.create("http://localhost:8080/persons"), MediaTypes.HAL_JSON);
        Link persons = traverson.follow("self")
                .asLink();
        logger.info(persons.getHref());
        return ResponseEntity.ok(persons.getHref());
    }

    @RequestMapping("/persons/{id}")
    public ResponseEntity<?> getPersonLinksById(@PathVariable Long id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        Traverson traverson = new Traverson(URI.create("http://localhost:8080/persons/" + id), MediaTypes.HAL_JSON);
        EntityModel<Person> entityModelEntityModel = traverson
                .follow("self")
                .toObject(new TypeReferences.EntityModelType<Person>());
        Person content = entityModelEntityModel.getContent();
        return ResponseEntity.ok(content);
    }

    @RequestMapping("/orders")
    public ResponseEntity<?> getOrders() {
        Traverson traverson = new Traverson(URI.create("http://localhost:8080/orders"), MediaTypes.HAL_JSON);
        TypeReferences.CollectionModelType<CollectionModel<Order>> typeRefDevices = new TypeReferences.CollectionModelType<CollectionModel<Order>>(){};
        Traverson.TraversalBuilder tb = traverson.follow("orderList");
        CollectionModel<CollectionModel<Order>> resUsers = tb.toObject(typeRefDevices);
        Collection<CollectionModel<Order>> content = resUsers.getContent();
        content.forEach(orders -> {
            System.out.println(orders.getContent());
            System.out.println(orders.getLinks());
        });
        return ResponseEntity.ok(content);
    }


}
