package microservice.queryservice.actionView;


import lombok.Getter;
import microservice.queryservice.action.ActionEvent.ActionCreatedEvent;
import microservice.queryservice.action.ActionEvent.ActionStatus;
import microservice.queryservice.action.ActionEvent.Progress;

@Getter
public class ActionView {
    private final String id;
    private final String name;
    private final Progress progress;
    private Integer cost;
    private ActionStatus status;

    public ActionView(String id, String name, Progress progress, Integer cost, ActionStatus status) {
        this.id = id;
        this.name = name;
        this.progress = progress;
        this.cost = cost;
        this.status = status;
    }

    public static ActionView create(String actionId, ActionCreatedEvent event) {
        return new ActionView(actionId, event.name(), event.progress(), null, event.status());
    }

    public void update(int cost) {
        this.cost = cost;
    }

    public void approve() {
        this.status = ActionStatus.CREATED;
    }
}
