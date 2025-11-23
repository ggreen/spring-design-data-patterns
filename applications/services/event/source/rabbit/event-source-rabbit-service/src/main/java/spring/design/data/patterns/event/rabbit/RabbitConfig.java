package spring.design.data.patterns.event.rabbit;

import org.jspecify.annotations.Nullable;
import org.springframework.amqp.rabbit.connection.ConnectionNameStrategy;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.converter.JacksonJsonMessageConverter;
import org.springframework.messaging.converter.MessageConverter;

/**
 * @author Gregory Green
 */
@Configuration
public class RabbitConfig {

    @Value("${spring.application.name:EventSourceService}")
    private String applicationName;
    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Bean
    MessageChannel messageChannel(RabbitMessagingTemplate rabbitMQTemplate) {

        return (msg, timeout) -> {
            rabbitMQTemplate.convertAndSend(exchange, msg.getPayload());
            return true;
        };
    }

    @Bean
    MessageConverter messageConverter(RabbitMessagingTemplate rabbitMessagingTemplate)
    {
        var converter = new JacksonJsonMessageConverter();
        rabbitMessagingTemplate.setMessageConverter(converter);
        return converter;
    }

    @Bean
    ConnectionNameStrategy connectionNameStrategy() {
        return connectionFactory -> applicationName;
    }
}
