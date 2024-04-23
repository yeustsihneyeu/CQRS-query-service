package microservice.queryservice.ticket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.queryservice.actionView.ActiveViewService;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(
        topics = "${topics.ticket}",
        groupId = "queryEventHandler",
        containerFactory = "ticketContainerFactory"
)
public class TicketEventHandler {

    private final ActiveViewService activeViewService;

    @KafkaHandler
    public void listen(TicketCreatedEvent message) {
        log.info("getting TICKET {}", message);
        activeViewService.update(message.getActionId(), message.getCost());
    }
}
