package spring.design.data.patterns.employee.near.cache;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.connection.stream.StringRecord;
import org.springframework.data.redis.core.RedisTemplate;
import showcase.streaming.event.account.domain.Location;
import spring.design.data.patterns.employee.near.cache.service.LocationBroadcastService;

import java.util.Map;
import java.util.Objects;

/**
 * @author Gregory Green
 */
@Configuration
public class MessagingConfig {

    @Value("${redis.stream.location.name:location}")
    private String streamName;

    @Bean
    LocationBroadcastService locationBroadcastService(RedisTemplate<String,Location> template, Converter<@NonNull Location,String> converter)
    {
        return location -> {
            StringRecord record = StreamRecords.string(Map.of(location.getId(),
                            Objects.requireNonNull(converter.convert(location))))
                    .withStreamKey(streamName);
            template.opsForStream().add(record);
        };
    }
}
