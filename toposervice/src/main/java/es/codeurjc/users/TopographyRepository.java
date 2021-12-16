package es.codeurjc.users;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TopographyRepository extends ReactiveMongoRepository<Topography, String> {

	Flux<Topography> findByCityIgnoreCase(String city);
}
