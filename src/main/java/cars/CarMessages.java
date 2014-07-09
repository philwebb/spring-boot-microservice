package cars;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.messaging.MessageChannel;

@Configuration
public class CarMessages {

	@Autowired
	private CarRepository repository;

	@Autowired
	private ConnectionFactory connectionFactory;

	@Autowired
	@Qualifier("errorChannel")
	private MessageChannel errorChannel;

	@Bean
	public IntegrationFlow carMessageFlow() {
		return IntegrationFlows
				.from(Amqp.inboundAdapter(this.connectionFactory, "cars")
						.errorChannel(this.errorChannel))
				.transform(Transformers.fromJson(Car.class))
				.handle(Car.class, (data, headers) -> {
					this.repository.save(data);
					return null;
				}).get();
	}

}
