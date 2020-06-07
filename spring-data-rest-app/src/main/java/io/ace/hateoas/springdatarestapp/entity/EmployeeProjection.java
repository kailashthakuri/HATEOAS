package io.ace.hateoas.springdatarestapp.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "employeeProjection", types = {Employee.class})
public interface EmployeeProjection {
    public String getFirstName();

    public String getLastName();

    public Department getDepartment();
}
