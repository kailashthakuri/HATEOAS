package io.ace.hateoas.springdatarestapp.repository;

import io.ace.hateoas.springdatarestapp.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "departments", collectionResourceRel = "departments")
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    //  http://localhost:8080/api/departments/search/byName?name=IT
    @RestResource(path = "byName")
    public Department findByName(@Param("name") String name);

}
