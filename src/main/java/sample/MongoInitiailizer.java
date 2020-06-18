package sample;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;
import sample.entities.Message;
import sample.repository.MessageRepository;
import sample.entities.User;
import sample.repository.UserRepository;


@Component
class MongoInitiailizer implements SmartInitializingSingleton {
	private final MessageRepository messages;
	private final UserRepository users;

	MongoInitiailizer(MessageRepository messages, UserRepository users) {
		this.messages = messages;
		this.users = users;
	}

	@Override
	public void afterSingletonsInstantiated() {
		// sha256 w/ salt encoded "password"
		String passsword = "73ac8218b92f7494366bf3a03c0c2ee2095d0c03a29cb34c95da327c7aa17173248af74d46ba2d4c";

		User fer = new User(1L, "fer@gmail.com", passsword, "Fer", "Santi");
		User luis = new User(100L, "luis@gmail.com", passsword, "luis", "Nicolas");

		this.users.save(fer).block();
		this.users.save(luis).block();

		this.messages.save(new Message(1L, fer, luis, "Hello World")).block();
		this.messages.save(new Message(100L, fer, luis,"Greetings KCDC")).block();

		this.users.findAll()
				.doOnNext(user -> user.setPassword("{sha256}" + user.getPassword()))
				.flatMap(this.users::save)
				.collectList()
				.block();		
	}
}
