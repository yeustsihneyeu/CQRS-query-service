package microservice.queryservice.action;

import lombok.Getter;

import java.util.List;

public record ActionEvent() {

    public record ActionCreatedEvent(String name, ActionStatus status, Progress progress) { }
    public record ActionApprovedEvent(ActionStatus status) { }

    public record Progress(List<DailyProgress> dailyProgressList) { }
    public record DailyProgress(String name, int duration) { }

    public enum ActionStatus {
        PENDING, CREATED, REJECTED
    }

    @Getter
    public enum ActionEventType {
        ACTION_CREATED("com.nikamicroservice.eventservice.event.event.ActionEvents$ActionCreatedEvent"),
        ACTION_APPROVED("com.nikamicroservice.eventservice.event.event.ActionEvents$ActionApproveEvent");

        private final String type;

        ActionEventType(String type) {
            this.type = type;
        }
    }
}
