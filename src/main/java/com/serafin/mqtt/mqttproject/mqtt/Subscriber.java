package com.serafin.mqtt.mqttproject.mqtt;

import com.serafin.mqtt.mqttproject.mqtt.SimpleMqttCallBack;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Scanner;

public class Subscriber {

  public static void main(String[] args) throws MqttException {

    Scanner scanner = new Scanner(System.in);
    System.out.println("Topic: ");
    String topic = scanner.nextLine();

    System.out.println("== START SUBSCRIBER ==");

    MqttClient client=new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
    client.setCallback( new SimpleMqttCallBack() );
    client.connect();

    client.subscribe(topic);
  }

}
