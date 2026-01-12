package spring.design.data.patterns.function;

import nyla.solutions.core.patterns.repository.memory.InMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.data.patterns.employee.domains.employee.records.Location;

/**
 * @author Gregory Green
 */
@Configuration
public class RepositoryConfig {

    @Bean InMemoryRepository<Location,String> repository()
    {
        return  InMemoryRepository.builder()
                .withIdProperty("id")
                .build();
    }
}
