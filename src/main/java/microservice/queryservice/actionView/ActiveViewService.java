package microservice.queryservice.actionView;


import lombok.extern.slf4j.Slf4j;
import microservice.queryservice.action.ActionEvent.ActionStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ActiveViewService {

    public ActiveViewService(ActionViewRepository actionViewRepository) {
        this.actionViewRepository = actionViewRepository;
    }

    private final ActionViewRepository actionViewRepository;

    public List<ActionView> getAllActions() {
        return actionViewRepository.findAll();
    }

    public void create(ActionView actionView) {
        log.info("create active view {}", actionView);
        actionViewRepository.save(actionView);
    }

    public void updateStatus(String actionId, ActionStatus status) {
        log.info("update status {}", status);
        ActionView view = actionViewRepository.findById(actionId).orElseThrow();
        view.approve();
        actionViewRepository.save(view);
    }

    public void update(String actionId, int cost) {
        log.info("update cost {}", cost);
        ActionView view = actionViewRepository.findById(actionId).orElseThrow();
        view.update(cost);
        actionViewRepository.save(view);
    }

    public ActionView getById(String id) {
        log.info("get by id {}", id);
        return actionViewRepository.findById(id).orElseThrow();
    }
}
