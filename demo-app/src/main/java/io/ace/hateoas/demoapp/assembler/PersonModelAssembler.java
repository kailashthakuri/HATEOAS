package io.ace.hateoas.demoapp.assembler;

import io.ace.hateoas.demoapp.entity.Person;
import io.ace.hateoas.demoapp.model.PersonModel;
import io.ace.hateoas.demoapp.resource.PersonController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class PersonModelAssembler extends RepresentationModelAssemblerSupport<Person, PersonModel> {
    public PersonModelAssembler() {
        super(PersonController.class, PersonModel.class);
    }

    @Override
    public PersonModel toModel(Person entity) {
        PersonModel personModel = new PersonModel();
        personModel.setPersonName(entity.getName());
        personModel.setId(entity.getId());
        return personModel;
    }
}
