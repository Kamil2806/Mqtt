package com.serafin.mqtt.mqttproject.simple;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Scanner;

public class Publisher {

  public static FileService fileService = new FileService();

  public static void main(String[] args) throws MqttException {

    Scanner scanner = new Scanner(System.in);
    System.out.println("Topic: ");
    String topic = scanner.nextLine();
    System.out.println("Message to publish: ");
    String messageString = scanner.nextLine();

    if (args.length == 2 ) {
      messageString = args[1];
    }

    System.out.println("== START PUBLISHER ==");


    MqttClient client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
    client.connect();
    MqttMessage message = new MqttMessage();
    message.setPayload(messageString.getBytes());
    fileService.writeMessage(topic, message);
    client.publish(topic, message);

    System.out.println("\tMessage '"+ messageString +" to " + topic);

    client.disconnect();

    System.out.println("== END PUBLISHER ==");
  }


}
