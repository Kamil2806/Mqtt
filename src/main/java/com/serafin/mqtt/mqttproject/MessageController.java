package com.serafin.mqtt.mqttproject;

import com.serafin.mqtt.mqttproject.simple.Message;
import com.serafin.mqtt.mqttproject.simple.MessageService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public List<Message> getMessages(@PathVariable String topic) {

        return messageService.getMessages(topic);
    }
}
