package spring.design.data.patterns.function.service;

import lombok.RequiredArgsConstructor;
import nyla.solutions.core.patterns.repository.SaveRepository;
import org.springframework.stereotype.Component;
import spring.data.patterns.employee.domains.employee.records.Location;

import java.util.function.Consumer;

/**
 * @author Gregory Green
 */
@RequiredArgsConstructor
@Component
public class SaveEmployee implements Consumer<Location> {

    private final SaveRepository<Location> repository;

    @Override
    public void accept(Location employee) {
        repository.save(employee);
    }
}
