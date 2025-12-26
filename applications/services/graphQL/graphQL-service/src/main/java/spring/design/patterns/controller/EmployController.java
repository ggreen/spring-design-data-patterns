package spring.design.patterns.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import spring.data.patterns.employee.domains.employee.records.Employee;
import spring.design.patterns.domain.EmployeeInput;
import spring.design.patterns.repository.EmployeeRepository;

/**
 * @author Gregory Green
 */
@Controller
@RequiredArgsConstructor
public class EmployController {
    private final EmployeeRepository employeeRepository;

    /**
     * Find employee by id
     * @param id the employee id
     * @return the employee or null if not found
     */
    @QueryMapping
    public Employee employeeById(@Argument String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Employee saveEmployee(@Argument EmployeeInput input) {
        var employee = Employee.builder()
                .id(input.id())
                .empName(input.empName())
                .jobName(input.jobName())
                .hireDate(input.hireDate())
                .salary(input.salary())
                .commission(input.commission())
                .department(input.department())
                .managerId(input.managerId())
                .build();
        employeeRepository.save(employee);
        return employee;
    }

}
