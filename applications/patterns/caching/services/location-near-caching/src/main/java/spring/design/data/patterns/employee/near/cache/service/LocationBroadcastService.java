package spring.design.data.patterns.employee.near.cache.service;

import org.springframework.stereotype.Service;
import showcase.streaming.event.account.domain.Location;

/**
 * @author Gregory Green
 */
@Service
@FunctionalInterface
public interface LocationBroadcastService {
    void publish(Location location);
}
