package io.ace.hateoas.demoapp.resource;


import io.ace.hateoas.demoapp.assembler.PersonModelAssembler;
import io.ace.hateoas.demoapp.entity.Person;
import io.ace.hateoas.demoapp.model.PersonModel;
import io.ace.hateoas.demoapp.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@ExposesResourceFor(Person.class)
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    EntityLinks entityLinks;

    @Autowired
    PersonModelAssembler personModelAssembler;

    @Autowired
    private PersonService personService;

    Logger logger = LoggerFactory.getLogger(PersonController.class);

//    @GetMapping(value = "/{id}", produces = "application/hal+json")
//    public ResponseEntity<EntityModel<Person>> getById(@PathVariable Long id) {
//        Link selfLink = linkTo(methodOn(PersonController.class).getById(id)).withSelfRel();
//        selfLink
//                .andAffordance(afford(methodOn(PersonController.class).update(null, id)))
//                .andAffordance(afford(methodOn(PersonController.class).delete(id)));
//        return personService.getPersonById(id)
//                .map(person -> EntityModel.of(person, selfLink))
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//
//    }

    @GetMapping
    public CollectionModel<PersonModel> getAll() {
        Link link = entityLinks.linkToCollectionResource(Person.class);
        List<PersonModel> people = personService.getPeople().stream()
                .map(personModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(people, link);
    }

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public EntityModel<Person> getById(@PathVariable Long id) {
        return this.personService.getPersonById(id)
                .map(person -> {
                    Link selfLink = entityLinks.linkToItemResource(Person.class, person.getId());
                    return EntityModel.of(person, selfLink);
                })
                .orElse(EntityModel.of(new Person()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody EntityModel<Person> person, @PathVariable Long id) {
        logger.info("Person Updated Successfully");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        logger.info("Person Deleted Successfully");
        return ResponseEntity.ok().build();
    }
}
