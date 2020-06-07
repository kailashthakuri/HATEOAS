package io.ace.hateoas.demoapp.resource;

import io.ace.hateoas.demoapp.entity.Department;
import io.ace.hateoas.demoapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.hateoas.server.core.EmbeddedWrappers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping({"/{id}"})
    public RepresentationModel<?> getEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(employee -> {
                    Optional<Department> departmentOptional = employeeService.getDepartmentByEmployee(id);
                    Department department = departmentOptional.orElse(null);
                    Link deptLink = Link.of("/department/{id}")
                            .expand(employee.getDepartmentId())
                            .withRel("department");
                    EmbeddedWrappers embeddedWrappers = new EmbeddedWrappers(false);
                    RepresentationModel<?> build = HalModelBuilder
                            .halModel(embeddedWrappers)
                            .preview(employee)
                            .forLink(Link.of("/employees/{id}").expand(id).withRel("employee"))
                            .embed(department)
                            .link(deptLink)
                            .build();
                    return build;
                })
                .orElse(null);
    }

    @GetMapping
    public RepresentationModel<?> getEmployees() {
        RepresentationModel<?> build = HalModelBuilder
                .halModelOf(employeeService.employees().get(0))
                .embed(employeeService.employees())
                .link(Link.of("/").withSelfRel())
                .build();
        return build;
    }
}

