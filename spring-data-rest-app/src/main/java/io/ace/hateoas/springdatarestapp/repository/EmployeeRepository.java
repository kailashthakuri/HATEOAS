package io.ace.hateoas.springdatarestapp.repository;

import io.ace.hateoas.springdatarestapp.entity.Employee;
import io.ace.hateoas.springdatarestapp.entity.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = EmployeeProjection.class, collectionResourceRel = "employees", path = "employees")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Save Employee
     * method: PUT
     * content-type: application/json
     * url: http://localhost:8080/api/employees
     * body: {"firstName":"Ram","lastName":"Limbu","department": "http://localhost:8080/api/departments/2"}
     */

}
