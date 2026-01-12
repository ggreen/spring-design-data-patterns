package spring.design.data.patterns.employee.near.cache.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import showcase.streaming.event.account.domain.Location;
import spring.design.data.patterns.employee.near.cache.repository.LocationRepository;

/**
 * @author Gregory Green
 */
@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository repository;
    private final LocationBroadcastService locationBroadcastService;
    public void saveLocation(Location location) {

        locationBroadcastService.publish(location);
    }

    public Location getLocation(String id) {
        return repository.findById(id).orElse(null);
    }
}
