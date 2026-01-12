package spring.design.data.patterns.employee.near.cache.service;

import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import showcase.streaming.event.account.domain.Location;
import spring.design.data.patterns.employee.near.cache.repository.LocationRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    private final Location employee = JavaBeanGeneratorCreator.of(Location.class)
            .create();
    private LocationService subject;
    @Mock
    private LocationRepository repository;
    @Mock
    private LocationBroadcastService broadcast;

    @BeforeEach
    void setUp() {
        subject = new LocationService(repository,broadcast);
    }

    @Test
    void saveLocation() {
        subject.saveLocation(employee);

        verify(broadcast).publish(employee);
    }

    @Test
    void readLocation() {

        when(repository.findById(anyString())).thenReturn(Optional.of(employee));

        var actual = subject.getLocation(employee.getId());

        assertThat(actual).isEqualTo(employee);
    }
}