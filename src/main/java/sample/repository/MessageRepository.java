package sample.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sample.entities.Message;


@Component
public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {
	Flux<Message> findByTo(Long id);

	@PostAuthorize("returnObject?.to?.id == principal?.id")
	Mono<Message> findById(Long id);
}
