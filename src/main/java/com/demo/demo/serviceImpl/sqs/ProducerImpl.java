package com.demo.demo.serviceImpl.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.demo.demo.dto.sqs.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class ProducerImpl {

    @Value("${aws.queueName}")
    private String queueName;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AmazonSQS amazonSQS;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    private final String queueUrl = "";


    public String sendMassage(String id, String msg) {
        try {
            String queueUrl = amazonSQS.getQueueUrl(queueName).getQueueUrl();
            var message = Message.builder().id(id).content(msg).data(new Date()).build();
            var result = amazonSQS.sendMessage(queueUrl, objectMapper.writeValueAsString(message));
            return " MSG Send.... ";
        } catch (Exception e) {
            log.error("Queue Exception Message: {} " + e.getMessage());
        }
        return " MSG not sent";
    }

    public String sendMsgToQueue(String msg) {
        queueMessagingTemplate.send(queueUrl, MessageBuilder.withPayload(msg).build());
        return "MSG Sent .. ";
    }

    @SqsListener(queueUrl)
    public void receiveMsg(Message message) {
        log.info(" msg is {} : " + message.getContent());
    }


}
