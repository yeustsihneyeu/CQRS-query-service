package microservice.queryservice.action;

import lombok.Data;
import microservice.queryservice.action.ActionEvent.ActionApprovedEvent;

@Data
public final class ActionApprovedMessage {
    private String id;
    private String entityId;
    private String entityType;
    private String eventType;
    private ActionApprovedEvent event;
}
