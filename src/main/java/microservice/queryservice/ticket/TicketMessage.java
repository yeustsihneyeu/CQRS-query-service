package microservice.queryservice.ticket;

import lombok.Data;

import java.util.Map;

@Data
public class TicketMessage {
    private String id;
    private Map<String, String> headers;
    private String payload;
}
