package spring.design.data.patterns.employee.near.cache;

import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import showcase.streaming.event.account.domain.Location;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

/**
 * @author Gregory Green
 */
@Configuration
public class SerializationConfig {

    @Bean
    RedisSerializer<?> serializer()
    {
        return new StringRedisSerializer();
    }
    @Bean
    Converter<@NonNull Location,String> locationToStringConverter(JsonMapper objectMapper)
    {
        return objectMapper::writeValueAsString;
    }

    @Bean
    Converter<@NonNull String, Location> stringToLocationConverter(JsonMapper objectMapper)
    {
        return source -> objectMapper.readValue(source, Location.class);
    }

}
