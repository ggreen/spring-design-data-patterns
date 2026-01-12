package spring.design.data.patterns.employee.near.cache;

import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import showcase.streaming.event.account.domain.Location;
import spring.design.data.patterns.employee.near.cache.service.LocationService;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

@EnabledIfSystemProperty(named = "integration", matches = "true")
@SpringBootTest
class LocationNearCacheAppTest {

    @Autowired
    LocationService locationService;
    private final Location location = JavaBeanGeneratorCreator.of(Location.class).create();

    @Test
    void main() throws InterruptedException {
        locationService.saveLocation(location);
        sleep(5000);
        var actual = locationService.getLocation(location.getId());
        assertEquals(location,actual);

    }
}