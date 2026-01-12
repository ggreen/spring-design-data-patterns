package spring.design.patterns;

import nyla.solutions.core.patterns.repository.memory.InMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.data.patterns.employee.domains.employee.records.Location;
import spring.design.patterns.repository.EmployeeRepository;

import java.util.Optional;

/**
 * @author Gregory Green
 */
@Configuration
public class RepositoryConfig {

    @Bean
    InMemoryRepository<Location, String> inMemoryRepository()
    {
        return InMemoryRepository.builder()
                .withIdProperty("id")
                .build();
    }

    @Bean
    EmployeeRepository employeeRepository(InMemoryRepository<Location, String> inMemoryRepository) {
        return new EmployeeRepository() {
            @Override
            public Optional<Location> findById(String id) {
                return inMemoryRepository.findById(id);
            }

            @Override
            public Location save(Location employee) {
                return inMemoryRepository.save(employee);
            }
        };
    }
}
