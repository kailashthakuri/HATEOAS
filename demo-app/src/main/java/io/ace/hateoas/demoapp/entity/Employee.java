package io.ace.hateoas.demoapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

@Data
@NoArgsConstructor
@Relation(collectionRelation = "employees")
@AllArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private Long departmentId;
}
