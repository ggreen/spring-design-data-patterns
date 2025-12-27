package spring.design.data.patterns.employee.controller;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import nyla.solutions.core.patterns.conversion.Converter;
import nyla.solutions.core.util.JavaBean;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import spring.data.patterns.employee.domains.employee.records.Employee;
import spring.design.data.patterns.employee.entities.EmployeeEntity;
import spring.design.data.patterns.employee.repository.EmployeeRepository;

/**
 * @author Gregory Green
 */
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final Converter<Employee, EmployeeEntity> toEntity
            = JavaBean.converter(EmployeeEntity.class);
    private final Converter<EmployeeEntity,Employee> toEmployee = JavaBean.converter(Employee.class);

    @PostMapping("employee")
    public void saveEmployer(@RequestBody Employee employee) {
        employeeRepository.save(toEntity.convert(employee));
    }

    @GetMapping("employee/{id}")
    @Cacheable(value = "employees")
    public @Nullable  Employee findEmployeeById(@PathVariable @Nonnull String id) {
        return toEmployee
                .convert(employeeRepository.findById(id).orElse(null));
    }
}
