package spring.design.data.patterns.employee.near.cache.repository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;
import showcase.streaming.event.account.domain.Location;

@Repository
public interface LocationRepository extends KeyValueRepository<Location, String> {
}
