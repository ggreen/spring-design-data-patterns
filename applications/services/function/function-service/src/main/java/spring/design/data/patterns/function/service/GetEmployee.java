package spring.design.data.patterns.function.service;

import lombok.RequiredArgsConstructor;
import nyla.solutions.core.patterns.repository.FindByIdRepository;
import org.springframework.stereotype.Component;
import spring.data.patterns.employee.domains.employee.records.Employee;

import java.util.function.Function;

/**
 * @author Gregory Green
 */
@RequiredArgsConstructor
@Component
public class GetEmployee implements Function<String,Employee> {

    private final FindByIdRepository<Employee,String> findByIdRepository;
    @Override
    public Employee apply(String employeeId) {
        return findByIdRepository.findById(employeeId).orElse(null);
    }
}
