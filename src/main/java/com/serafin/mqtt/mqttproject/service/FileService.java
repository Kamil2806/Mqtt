package com.serafin.mqtt.mqttproject.service;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    public void writeMessage(String topic, String content) {

        String line = "Topic: " + topic + "\n" + "Message: " + content + "\n\n";

        File file = new File("data/" + topic + ".txt");

        if(!file.isFile()) {
            createFileToWrite(topic);
        } else {

        }

        String outputPath = "data/" + topic + ".txt";

        writeToFile(line, outputPath);
    }

    private void createFileToWrite(String topic) {

        try {
            PrintWriter writer = new PrintWriter("data/" + topic + ".txt", "UTF-8");
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String line, String outputPath) {

        try {
            Files.write(Paths.get(outputPath), line.getBytes(), StandardOpenOption.APPEND);
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getDataFromFile(String input) {

        List<String> datas = new ArrayList<>();
        String inputPath = "data/input/" + input + ".txt";

        try {
            datas = Files.readAllLines(Paths.get(inputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return datas;
    }
}
