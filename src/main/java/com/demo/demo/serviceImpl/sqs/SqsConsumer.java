package com.demo.demo.serviceImpl.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Component
public class SqsConsumer {

    @Value("${aws.queueName}")
    private String queueName;

    @Autowired
    private AmazonSQS amazonSQS;

    // In this we are pulling the message from queue every 5sec. and in this way we are responsible for deleting the messages.
//    @Scheduled(fixedDelay = 2*1000)
    public void consumeMessage(){
        try {
            String queueUrl=amazonSQS.getQueueUrl(queueName).getQueueUrl();
            ReceiveMessageResult receiveMessageResult=amazonSQS.receiveMessage(queueUrl);
            if(!receiveMessageResult.getMessages().isEmpty()){
                Message message=receiveMessageResult.getMessages().get(0);
                log.info("Read Message From Queue By -1{} : "+message.getBody());
                amazonSQS.deleteMessage(queueUrl,message.getReceiptHandle());
            }
        }catch (Exception e){
            log.error("Queue Exception Message: {}", e.getMessage());
        }
    }

    @Scheduled(fixedDelay = 2*1000)
    public void consumeMessage2(){
        try {
            String queueUrl=amazonSQS.getQueueUrl(queueName).getQueueUrl();
            ReceiveMessageResult receiveMessageResult=amazonSQS.receiveMessage(queueUrl);
            if(!receiveMessageResult.getMessages().isEmpty()){
                Message message=receiveMessageResult.getMessages().get(0);
                log.info("Read Message From Queue By -2 {} : "+message.getBody());
                amazonSQS.deleteMessage(queueUrl,message.getReceiptHandle());
            }
        }catch (Exception e){
            log.error("Queue Exception Message: {}", e.getMessage());
        }
    }

//    @SqsListener("${aws.queueName}")
//    public void consumeMessageFromSqsQueue(String  msg){
//        log.info("Read msg from queue {} "+ msg);
//        Message message=new ObjectMapper().convertValue(msg,Message.class);
//    }
}
