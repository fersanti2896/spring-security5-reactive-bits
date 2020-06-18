package sample.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import sample.entities.User;


public interface UserRepository extends ReactiveCrudRepository<User, Long> {
	Mono<User> findByEmail(String email);
}
