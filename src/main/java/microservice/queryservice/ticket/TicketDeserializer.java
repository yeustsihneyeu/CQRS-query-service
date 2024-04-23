package microservice.queryservice.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class TicketDeserializer implements Deserializer<Object> {

    private final ObjectMapper mapper;

    public TicketDeserializer(ObjectMapper objectMapper) {
        this.mapper = objectMapper;
    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        try {
            TicketMessage ticketMessage = mapper.readValue(data, TicketMessage.class);
            return mapper.readValue(ticketMessage.getPayload(), TicketCreatedEvent.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
