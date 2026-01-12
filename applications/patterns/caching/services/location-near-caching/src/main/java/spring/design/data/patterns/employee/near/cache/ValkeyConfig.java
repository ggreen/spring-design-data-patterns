package spring.design.data.patterns.employee.near.cache;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.cache.autoconfigure.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import showcase.streaming.event.account.domain.Location;
import spring.design.data.patterns.employee.near.cache.listener.LocationStreamListener;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;

/**
 * @author Gregory Green
 */
@Configuration
@EnableCaching
public class ValkeyConfig {

    @Value("${redis.stream.location.name:location}")
    private String streamName;

    @Value("${app.cache.ttl.seconds:10}")
    private long ttlSeconds;

    @Bean
    RedisTemplate<String, showcase.streaming.event.account.domain.Location> redisTemplate(RedisConnectionFactory connectionFactory,
                                                                                          @Nullable RedisSerializer<?> serializer) {

        RedisTemplate<String, Location> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setDefaultSerializer(serializer);
        return template;
    }

    @Bean
    StreamMessageListenerContainer<String, MapRecord<String, String, String>> listener(RedisConnectionFactory connectionFactory, LocationStreamListener locationLister){

        StreamListener<String, MapRecord<String, String, String>> listener = (StreamListener)locationLister;
        var containerOptions = StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                .builder().pollTimeout(Duration.ofMillis(100)).build();

        var container = StreamMessageListenerContainer.create(connectionFactory,
                containerOptions);

        var subscription = container.receive(StreamOffset.fromStart(streamName), listener);

        container.start();
        return container;

    }

    /**
     * Use JSON serialization for Redis cache values
     * @param objectMapper the object mapper
     * @return the redis cache manager customizer
     */
    @Bean
    public RedisCacheManagerBuilderCustomizer myRedisCacheManagerBuilderCustomizer(ObjectMapper objectMapper) {
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext
                        .SerializationPair.fromSerializer(new GenericJacksonJsonRedisSerializer(objectMapper)));

        return (builder) -> builder
                .cacheDefaults(cacheConfig);
    }
}
