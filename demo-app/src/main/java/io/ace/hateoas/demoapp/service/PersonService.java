package io.ace.hateoas.demoapp.service;

import io.ace.hateoas.demoapp.entity.Person;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    List<Person> people = Arrays.asList(new Person(1L, "kailash"),
            new Person(2L, "nirjan"));


    public List<Person> getPeople() {
        return people;
    }

    public Optional<Person> getPersonById(Long id) {
        return people.stream()
                .filter(person -> person.getId() == id)
                .findFirst();
    }
}
