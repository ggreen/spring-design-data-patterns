package spring.design.data.patterns.event.source;

import com.fasterxml.jackson.databind.ObjectMapper;
import nyla.solutions.core.patterns.integration.Publisher;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.function.context.config.JsonMessageConverter;
import org.springframework.cloud.function.json.JacksonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.converter.MessageConverter;
import spring.data.patterns.employee.domains.employee.records.Employee;

/**
 * @author Gregory Green
 */
@Configuration
public class KafkaConfig {

    @Value("${spring.cloud.stream.bindings.output.destination:employees}")
    private String employTopic;

    @Value("${kafka.topic.partitions:3}")
    private int partitions;

    @Value("${kafka.topic.replicas:1}")
    private int replicas;

    @Bean
    Publisher<Employee> employeePublisher(KafkaTemplate<String,Employee> template)
    {
        return employee -> template.send(employTopic,employee.id(),employee);
    }

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name(employTopic)
                .partitions(partitions)
                .replicas(replicas)
                .compact()
                .build();
    }

    @Bean
    MessageConverter convert(ObjectMapper objectMapper){
        return new JsonMessageConverter(
                new JacksonMapper(objectMapper));
    }
}
