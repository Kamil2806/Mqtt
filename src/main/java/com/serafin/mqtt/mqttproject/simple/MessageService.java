package com.serafin.mqtt.mqttproject.simple;

import com.serafin.mqtt.mqttproject.MessageDTO;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    private FileService fileService;

    @Autowired
    public MessageService(MessageRepository messageRepository, FileService fileService) {
        this.messageRepository = messageRepository;
        this.fileService = fileService;
    }

    public void sendMessage(MessageDTO messageDTO) throws MqttException {

        MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(messageDTO.getContent().getBytes());
        fileService.writeMessage(messageDTO.getTopic(), messageDTO.getContent());
        saveMessage(messageDTO);
        client.publish(messageDTO.getTopic(), message);
        client.disconnect();
    }

    private void saveMessage(MessageDTO messageDTO) {

        Message message = messageDTO.toEntity();
        messageRepository.save(message);
    }

    public void showMessage(String topic) throws MqttException {

        MqttClient client=new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
        client.setCallback( new SimpleMqttCallBack() );
        client.connect();
        client.subscribe(topic);
    }

    public List<Message> getMessages(String topic) {

        return messageRepository.findMessagesByTopic(topic);
    }
}
