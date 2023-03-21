package ptit.edu.kafkaexample.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @KafkaListener(topics = "topic-1",groupId = "group-1")
    public void receiveMessage(String message){
        System.out.println("##############");
        System.out.println("Receive message in consumer: "+message);
    }
}
