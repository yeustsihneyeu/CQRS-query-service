package microservice.queryservice.action;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import microservice.queryservice.action.ActionEvent.ActionApprovedEvent;
import microservice.queryservice.action.ActionEvent.ActionCreatedEvent;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Objects;

import static microservice.queryservice.action.ActionEvent.ActionEventType.ACTION_APPROVED;
import static microservice.queryservice.action.ActionEvent.ActionEventType.ACTION_CREATED;

public class ActionDeserializer implements Deserializer<Object> {

    private final ObjectMapper mapper;

    public ActionDeserializer(ObjectMapper objectMapper) {
        this.mapper = objectMapper;
    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        try {

            JsonNode jsonNode = mapper.readTree(data);
            String eventType = jsonNode.get("eventType").asText();
            String eventData = jsonNode.get("eventData").asText();

            if (Objects.equals(eventType, ACTION_APPROVED.getType())) {
                ActionApprovedMessage msg = mapper.readValue(data, ActionApprovedMessage.class);
                ActionApprovedEvent event = mapper.readValue(eventData, ActionApprovedEvent.class);
                msg.setEvent(event);
                return msg;
            } else if (Objects.equals(eventType, ACTION_CREATED.getType())) {
                ActionCreatedMessage msg = mapper.readValue(data, ActionCreatedMessage.class);
                ActionCreatedEvent event = mapper.readValue(eventData, ActionCreatedEvent.class);
                msg.setEvent(event);
                return msg;
            } else {
                throw new RuntimeException("type noy found");
            }
        }
        catch (Exception ex) {
            throw new SerializationException("Can't deserialize data  from topic [" + topic + "]", ex);
        }
    }
}
