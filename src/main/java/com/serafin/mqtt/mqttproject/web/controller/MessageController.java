package com.serafin.mqtt.mqttproject.web.controller;

import com.serafin.mqtt.mqttproject.web.dto.MessageDTO;
import com.serafin.mqtt.mqttproject.model.Message;
import com.serafin.mqtt.mqttproject.service.MessageService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(value = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void sendMessage(@RequestBody MessageDTO messageDTO) throws MqttException {

        messageService.sendMessage(messageDTO);
    }

    @GetMapping(value = "/messages/subscribe/{topic}")
    public void showMessage(@PathVariable String topic) throws MqttException {

        messageService.showMessage(topic);
    }

    @GetMapping(value = "/messages/{topic}")
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getMessagesByTopic(@PathVariable String topic) {

        return messageService.getMessagesByTopic(topic);
    }

    @GetMapping(value = "/messages")
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getMessages() {

        return messageService.getMessages();
    }

    @GetMapping(value = "/messages/file/{input}")
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getMessagesFromFile(@PathVariable String input) {

        return messageService.getMessagesFromFile(input);
    }

    @GetMapping(value = "/messages/{topic}/avg")
    @ResponseStatus(HttpStatus.OK)
    public Integer getAverage(@PathVariable String topic) {

        return messageService.getAverage(topic);
    }

    @GetMapping(value = "/messages/{topic}/max")
    @ResponseStatus(HttpStatus.OK)
    public Integer getMax(@PathVariable String topic) {

        return messageService.getMax(topic);
    }

    @GetMapping(value = "/messages/{topic}/min")
    @ResponseStatus(HttpStatus.OK)
    public Integer getMin(@PathVariable String topic) {

        return messageService.getMin(topic);
    }
}
