package spring.design.data.patterns.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

/**
 * @author Gregory Green
 */
@Configuration
@EnableCaching
public class ValkeyConfig {


    @Value("${app.cache.ttl.seconds:10}")
    private long ttlSeconds;

//
//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//
////        RedisSerializationContext<String, String> redisSerializationContext = RedisSerializationContext.fromSerializer(serializer);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.findAndRegisterModules();
//        var cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
////                .entryTtl(Duration.ofSeconds(ttlSeconds))
//                .serializeKeysWith(redisSerializationContext.getKeySerializationPair())
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper))
//                )
//                .disableCachingNullValues();
//
//        return  RedisCacheManager.builder(connectionFactory).cacheDefaults(cacheConfiguration)
//                .build();
//    }

}
