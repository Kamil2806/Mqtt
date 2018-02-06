package com.serafin.mqtt.mqttproject.simple;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@Component
public class Publisher {

  private FileService fileService;

  private MessageService messageService;

  @Autowired
  public Publisher(FileService fileService, MessageService messageService) {
    this.fileService = fileService;
    this.messageService = messageService;
  }

  @PostConstruct
  public void runAtStart() throws MqttException {

    Scanner scanner = new Scanner(System.in);
    System.out.println("Topic: ");
    String topic = scanner.nextLine();
    System.out.println("Message to publish: ");
    String messageString = scanner.nextLine();

    System.out.println("== START PUBLISHER ==");

    MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
    client.connect();
    MqttMessage message = new MqttMessage();
    message.setPayload(messageString.getBytes());

    fileService.writeMessage(topic, message);

    messageService.saveMessage(messageString, topic);

    client.publish(topic, message);

    System.out.println("\tMessage '"+ messageString +" to " + topic);

    client.disconnect();

    System.out.println("== END PUBLISHER ==");
  }


}
