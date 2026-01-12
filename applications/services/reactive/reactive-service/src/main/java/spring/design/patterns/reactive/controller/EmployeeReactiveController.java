package spring.design.patterns.reactive.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import spring.data.patterns.employee.domains.employee.records.Location;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Gregory Green
 */
@RestController
@RequestMapping("employees")
public class EmployeeReactiveController {
    private final Map<String, Location> employees = new ConcurrentHashMap<>();

    @PostMapping
    public Mono<Void> saveEmployee(@RequestBody Location employee) {
        employees.put(employee.id(), employee);
        return Mono.empty();
    }

    @GetMapping("{id}")
    public Mono<Location> findById(@PathVariable String id) {
        Location employee = employees.get(id);
        return employee != null ? Mono.just(employee) : Mono.empty();
    }
}
