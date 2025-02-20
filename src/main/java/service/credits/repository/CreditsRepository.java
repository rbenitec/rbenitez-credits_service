package service.credits.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import service.credits.model.entities.Credits;

@Repository
public interface CreditsRepository extends ReactiveMongoRepository<Credits, String> {

}
