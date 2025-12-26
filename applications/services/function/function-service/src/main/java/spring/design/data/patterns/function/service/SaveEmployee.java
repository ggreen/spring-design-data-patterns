package spring.design.data.patterns.function.service;

import lombok.RequiredArgsConstructor;
import nyla.solutions.core.patterns.repository.SaveRepository;
import org.springframework.stereotype.Component;
import spring.data.patterns.employee.domains.employee.records.Employee;

import java.util.function.Consumer;

/**
 * @author Gregory Green
 */
@RequiredArgsConstructor
@Component
public class SaveEmployee implements Consumer<Employee> {

    private final SaveRepository<Employee> repository;

    @Override
    public void accept(Employee employee) {
        repository.save(employee);
    }
}
