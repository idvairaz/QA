package main.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProducerService {
    private int send_counter;
    MeterRegistry meterRegistry;
    Counter counter;

    private final KafkaTemplate<String, String> kafka;

    public KafkaMessageProducerService(KafkaTemplate<String, String> kafka,
                                       MeterRegistry meterRegistry) {
        this.kafka = kafka;
        this.meterRegistry = meterRegistry;
        counter=Counter
                .builder("send.count")
                .tag("author","ira")
                .register(meterRegistry);
    }

    public void send(String msg) {
        //считать количество отправленных сообщений
        counter.increment();
        send_counter++;
        kafka.send("applicant", msg + " counter: " + send_counter);
    }
}

