package spring.design.patterns.controller;

import org.springframework.web.bind.annotation.*;
import spring.data.patterns.employee.domains.employee.records.Employee;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Gregory Green
 */
@RestController
@RequestMapping("employees")
public class EmployeeController {
    private final Map<String, Employee> employees = new ConcurrentHashMap<>();

    @PostMapping
    public void saveEmployee(@RequestBody Employee employee) {
        employees.put(employee.id(),employee);
    }

    @GetMapping("{id}")
    public Employee findEmployeeById(@PathVariable String id) {
        return employees.get(id);
    }
}
