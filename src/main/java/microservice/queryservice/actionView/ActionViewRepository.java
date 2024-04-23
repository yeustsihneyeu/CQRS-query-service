package microservice.queryservice.actionView;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActionViewRepository extends MongoRepository<ActionView, String> {

}
