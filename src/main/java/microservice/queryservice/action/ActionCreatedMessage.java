package microservice.queryservice.action;

import lombok.Data;
import microservice.queryservice.action.ActionEvent.ActionCreatedEvent;

@Data
public final class ActionCreatedMessage {
    private String id;
    private String entityId;
    private String entityType;
    private String eventType;
    private ActionCreatedEvent event;
}
