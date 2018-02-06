package com.serafin.mqtt.mqttproject;

import com.serafin.mqtt.mqttproject.simple.Message;

public class MessageDTO {

    private String topic;

    private String content;

    public MessageDTO(String topic, String content) {
        this.topic = topic;
        this.content = content;
    }

    public MessageDTO() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Message toEntity() {

        Message message = new Message();
        message.setTopic(this.topic);
        message.setContent(this.content);
        return message;
    }
}
