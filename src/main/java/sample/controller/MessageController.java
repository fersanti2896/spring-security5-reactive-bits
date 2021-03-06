package sample.controller;

import reactor.core.publisher.Mono;
import sample.entities.CurrentUser;
import sample.entities.User;
import sample.repository.MessageRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.result.view.Rendering;

@Controller
public class MessageController {
	private final MessageRepository messages;

	public MessageController(MessageRepository messages) {
		this.messages = messages;
	}

	@GetMapping("/inbox")
	Rendering inbox(@CurrentUser User user) {
		return Rendering.view("messages/inbox")
				.modelAttribute("messages", this.messages.findByTo(user.getId()))
				.build();
	}


	@GetMapping("/messages/{id}")
	Rendering message(@PathVariable Long id) {
		return Rendering.view("messages/view")
				.modelAttribute("message", this.messages.findById(id))
				.build();
	}

	@DeleteMapping("/messages/{id}")
	Mono<String> delete(@PathVariable Long id) {
		return this.messages.deleteById(id)
				.then(Mono.just("redirect:/inbox"));
	}
}
