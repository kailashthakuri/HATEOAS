package io.ace.hateoas.demoapp.service;

import io.ace.hateoas.demoapp.entity.Department;
import io.ace.hateoas.demoapp.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    List<Employee> employees = Arrays.asList(new Employee(1L, "kailash", 1L),
            new Employee(2L, "nirajan", 2L));

    List<Department> departments = Arrays.asList(new Department(1L, "IT"),
            new Department(2L, "Account"));

    public List<Employee> employees() {
        return employees;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst();
    }

    public Optional<Department> getDepartmentByEmployee(Long departmentId) {
        return departments
                .stream()
                .filter(department -> department.getId() == departmentId)
                .findFirst();
    }

}
