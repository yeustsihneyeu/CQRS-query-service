package microservice.queryservice.actionView;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/action-views")
@RequiredArgsConstructor
public class ActionViewController {

    private final ActiveViewService activeViewService;

    @GetMapping
    public List<ActionView> getAll() {
        return activeViewService.getAllActions();
    }

    @GetMapping("/{id}")
    public ActionView getByActionId(@PathVariable String id) {
        return activeViewService.getById(id);
    }
}
