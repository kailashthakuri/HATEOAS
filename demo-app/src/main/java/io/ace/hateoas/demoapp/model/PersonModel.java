package io.ace.hateoas.demoapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Relation(collectionRelation = "persons",value = "person")
public class PersonModel extends RepresentationModel<PersonModel> {
    private Long id;
    private String personName;
}
