package main.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaMessageConsumerService {
	List<String> messages=new ArrayList<>();
	MeterRegistry meterRegistry;
	Counter counter;
	private static final Logger LOG = LoggerFactory.getLogger(KafkaMessageConsumerService.class);

	public KafkaMessageConsumerService(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
		counter=Counter
				.builder("onMessage.count")
				.tag("author","ira")
				.register(meterRegistry);
	}


	@KafkaListener(topics = "applicant")
	public void onMessage(@Payload String msg) {
		counter.increment();
		messages.add(msg);
		LOG.info("Message consumed {}", msg);
	}
	public List<String> getAll(){
		List<String> res= new ArrayList<>(messages);
		messages.clear();
		return res;
	}
}

