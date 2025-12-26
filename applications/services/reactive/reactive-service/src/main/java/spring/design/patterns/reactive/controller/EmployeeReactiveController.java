package spring.design.patterns.reactive.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import spring.data.patterns.employee.domains.employee.records.Employee;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Gregory Green
 */
@RestController
@RequestMapping("employees")
public class EmployeeReactiveController {
    private final Map<String, Employee> employees = new ConcurrentHashMap<>();

    @PostMapping
    public Mono<Void> saveEmployee(@RequestBody Employee employee) {
        employees.put(employee.id(), employee);
        return Mono.empty();
    }

    @GetMapping("{id}")
    public Mono<Employee> findById(@PathVariable String id) {
        Employee employee = employees.get(id);
        return employee != null ? Mono.just(employee) : Mono.empty();
    }
}
