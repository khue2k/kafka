package ptit.edu.kafkaexample.service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ptit.edu.kafkaexample.constants.Constants;

import java.util.concurrent.CompletableFuture;

@Service
public class ProducerService implements ApplicationRunner {
    private KafkaTemplate kafkaTemplate;

    private ProducerService(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    private void sendMessage(String topics,String message){
       CompletableFuture<SendResult<String, String>> future= (CompletableFuture<SendResult<String, String>>) kafkaTemplate.send(topics, message);
        future.whenComplete((result, throwable) -> {
            if (throwable==null){
                System.out.println("Sent message: [ "+message+" ] ,offSet: [ "+result.getRecordMetadata().offset()+" ] ,Topic: [ "+result.getRecordMetadata().topic());
            }
            else {
                System.out.println("Unable to sent message :[ "+message+" ] because "+throwable.getMessage());
            }
        });
    }


    @Override
    public void run(ApplicationArguments args)  {
        for (int i=0;i<10;i++){
            sendMessage(Constants.TOPIC_NAME,"message "+i);
        }
    }
}
