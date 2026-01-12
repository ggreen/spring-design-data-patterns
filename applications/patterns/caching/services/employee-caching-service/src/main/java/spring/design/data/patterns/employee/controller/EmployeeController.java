package spring.design.data.patterns.employee.controller;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import nyla.solutions.core.patterns.conversion.Converter;
import nyla.solutions.core.util.JavaBean;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import spring.data.patterns.employee.domains.employee.records.Location;
import spring.design.data.patterns.employee.entities.EmployeeEntity;
import spring.design.data.patterns.employee.repository.EmployeeRepository;

/**
 * @author Gregory Green
 */
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@CacheConfig("employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final Converter<Location, EmployeeEntity> toEntity
            = JavaBean.converter(EmployeeEntity.class);
    private final Converter<EmployeeEntity, Location> toEmployee = JavaBean.converter(Location.class);

    @PostMapping("employee")
    @CachePut(key = "#employee.id")
    public void saveEmployer(@RequestBody Location employee) {
        employeeRepository.save(toEntity.convert(employee));
    }

    @GetMapping("employee/{id}")
    @Cacheable
    public @Nullable Location findEmployeeById(@PathVariable @Nonnull String id) {
        return toEmployee
                .convert(employeeRepository.findById(id).orElse(null));
    }


}
