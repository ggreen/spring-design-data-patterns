package spring.design.data.patterns.employee;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;

/**
 * @author Gregory Green
 */
@Configuration
@EnableCaching
public class ValkeyConfig {


    @Value("${app.cache.ttl.seconds:10}")
    private long ttlSeconds;

    @Bean
    public RedisSerializer<String> serializer()
    {
        return  (RedisSerializer) new GenericJacksonJsonRedisSerializer(new ObjectMapper());
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory, RedisSerializer<String> serializer) {

        RedisSerializationContext<String, String> redisSerializationContext = RedisSerializationContext.fromSerializer(serializer);

        var cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(ttlSeconds))
                .serializeKeysWith(redisSerializationContext.getKeySerializationPair())
                .serializeValuesWith(redisSerializationContext.getValueSerializationPair())
                .disableCachingNullValues();

        return  RedisCacheManager.builder(connectionFactory).cacheDefaults(cacheConfiguration)
                .build();
    }
}
