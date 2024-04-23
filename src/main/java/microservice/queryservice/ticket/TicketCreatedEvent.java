package microservice.queryservice.ticket;

import lombok.Data;

@Data
public class TicketCreatedEvent {
    private String actionId;
    private Integer cost;
}
