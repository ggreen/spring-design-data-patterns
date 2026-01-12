package spring.design.data.patterns.employee.near.cache.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nyla.solutions.core.util.Debugger;
import org.jspecify.annotations.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;
import showcase.streaming.event.account.domain.Location;
import spring.design.data.patterns.employee.near.cache.repository.LocationRepository;

import java.util.Objects;

/**
 * @author Gregory Green
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LocationStreamListener implements StreamListener<@NonNull String, @NonNull MapRecord<@NonNull String, @NonNull String, @NonNull String>> {
    private final LocationRepository repository;
    private final Converter<@NonNull String, Location> converter;
    @Override
    public void onMessage(MapRecord<@NonNull  String, @NonNull String, @NonNull String> msg) {
        log.info("Received location stream message={}",msg);

        try{
            msg.getValue().forEach((key, value) ->{
                log.info("Saving location from stream key={} value={}",key,value);
                Location location = converter.convert(value);
                repository.save(location);
            } );
        }
        catch (RuntimeException e)
        {
            log.error("Failed to process location stream message: {}, error: {}",
                    msg, Debugger.stackTrace(e));
            throw e;
        }


    }
}
