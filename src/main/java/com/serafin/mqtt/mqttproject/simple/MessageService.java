package com.serafin.mqtt.mqttproject.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveMessage(String content, String topic) {
        Message message =  createMessage(content, topic);
        messageRepository.save(message);

    }

    private Message createMessage(String content, String topic) {
        Message message = new Message();
        message.setContent(content);
       // message.setCreated(LocalDateTime.now());
        message.setTopic(topic);
        return message;
    }
}
