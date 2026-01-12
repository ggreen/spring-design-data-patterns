package spring.design.data.patterns.employee.near.cache.listener;

import jakarta.validation.constraints.NotNull;
import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.connection.stream.MapRecord;
import showcase.streaming.event.account.domain.Location;
import spring.design.data.patterns.employee.near.cache.repository.LocationRepository;
import tools.jackson.databind.json.JsonMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class LocationStreamListenerTest {

    private LocationStreamListener subject;
    @Mock
    private LocationRepository repository;
    @Mock
    private @NotNull MapRecord<@NotNull String, @NotNull String,  @NotNull String> msg;
    private final @NotNull Location employee = JavaBeanGeneratorCreator.of(Location.class).create();
    private JsonMapper mapper = new JsonMapper();
    @Mock
    private Converter<@NonNull String, Location> converter;

    @BeforeEach
    void setUp() {
        subject = new LocationStreamListener(repository,converter);
    }

    @Test
    void onMessage() {

        when(msg.getValue()).thenReturn(
                java.util.Map.of("key",
                        mapper.writeValueAsString(employee))
        );

        subject.onMessage(msg);

        verify(repository,atLeastOnce()).save(any());
    }
}