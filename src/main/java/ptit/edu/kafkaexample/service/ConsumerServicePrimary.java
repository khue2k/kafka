package ptit.edu.kafkaexample.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServicePrimary {
    @KafkaListener(topics = "topic-1",groupId = "group-1")
    public void  receiveMessagePrimary(String message){
        System.out.println("##############");
        System.out.println("Receive message in primary consumer : "+message);
    }
}
