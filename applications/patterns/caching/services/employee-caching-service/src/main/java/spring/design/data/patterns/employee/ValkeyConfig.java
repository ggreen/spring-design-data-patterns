package spring.design.data.patterns.employee;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.cache.autoconfigure.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.*;
import tools.jackson.databind.ObjectMapper;

/**
 * @author Gregory Green
 */
@Configuration
@EnableCaching
public class ValkeyConfig {


    @Value("${app.cache.ttl.seconds:10}")
    private long ttlSeconds;


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
