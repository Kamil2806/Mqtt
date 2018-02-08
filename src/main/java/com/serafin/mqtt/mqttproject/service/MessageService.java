package com.serafin.mqtt.mqttproject.service;

import com.serafin.mqtt.mqttproject.exeptions.MessageNotFoundException;
import com.serafin.mqtt.mqttproject.model.Message;
import com.serafin.mqtt.mqttproject.mqtt.SimpleMqttCallBack;
import com.serafin.mqtt.mqttproject.repository.MessageRepository;
import com.serafin.mqtt.mqttproject.service.FileService;
import com.serafin.mqtt.mqttproject.web.dto.MessageDTO;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        //message.setQos(1);
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
        client.subscribe(topic, 1);
    }

    public List<Message> getMessagesByTopic(String topic) {

        return messageRepository.findMessagesByTopic(topic);
    }

    public List<Message> getMessages() {

        return messageRepository.findAll(
                new Sort(
                        new Sort.Order(
                                Sort.Direction.ASC, "topic"
                        ),
                        new Sort.Order(
                                Sort.Direction.ASC, "id"
                        )
                ));
    }

    public List<Message> getMessagesFromFile(String input) {

        List<String> data = fileService.getDataFromFile(input);

        for (String datum : data) {

            Message message = new Message();
            message.setTopic(input);
            message.setContent(datum);
            messageRepository.save(message);
        }

        return messageRepository.findMessagesByTopic(input);
    }

    public Integer getAverage(String topic) {

        List<Message> messages = new ArrayList<>();
        messages = messageRepository.findMessagesByTopic(topic);
        Integer sum = 0;
        Integer size = messages.size();

        for (Message message : messages) {
            Integer num = Integer.parseInt(message.getContent());
            sum = sum + num;
        }

        return sum / size;
    }

    public Integer getMin(String topic) {

        List<Message> messages = new ArrayList<>();
        messages = messageRepository.findMessagesByTopic(topic);
        List<Integer> data = new ArrayList<>();

        for (Message message : messages) {
            data.add(Integer.parseInt(message.getContent()));
        }

        Collections.sort(data);
        return data.get(0);
    }

    public Integer getMax(String topic) {

        List<Message> messages = new ArrayList<>();
        messages = messageRepository.findMessagesByTopic(topic);
        List<Integer> data = new ArrayList<>();

        for (Message message : messages) {
            data.add(Integer.parseInt(message.getContent()));
        }

        Collections.sort(data);
        return data.get(data.size()-1);
    }
}
