package microservice.queryservice.action;

import lombok.extern.slf4j.Slf4j;
import microservice.queryservice.actionView.ActionView;
import microservice.queryservice.actionView.ActiveViewService;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@KafkaListener(
        topics = "${topics.action}",
        groupId = "queryEventHandler",
        containerFactory = "actionContainerFactory"
)
public class ActionEventHandler {

    private final ActiveViewService activeViewService;

    public ActionEventHandler(ActiveViewService activeViewService) {
        this.activeViewService = activeViewService;
    }

    @KafkaHandler
    public void listen(ActionCreatedMessage message) {
        log.info("getting CREATE Action {}", message);
        ActionView actionView = ActionView.create(message.getEntityId(), message.getEvent());
        activeViewService.create(actionView);
    }

    @KafkaHandler
    public void listen(ActionApprovedMessage message) {
        log.info("getting APPROVE Action {}", message);
        activeViewService.updateStatus(message.getEntityId(), message.getEvent().status());
    }
}
