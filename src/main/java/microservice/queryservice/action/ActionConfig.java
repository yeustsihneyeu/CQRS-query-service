package microservice.queryservice.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class ActionConfig {

    private final ObjectMapper objectMapper;

    @Bean
    public ConsumerFactory<String, Object> actionConsumerFactory() {

        HashMap<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://localhost:9092");

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new ActionDeserializer(objectMapper));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> actionContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(actionConsumerFactory());
        return factory;
    }
}
