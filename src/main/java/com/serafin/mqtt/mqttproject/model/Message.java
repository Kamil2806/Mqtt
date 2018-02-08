package com.serafin.mqtt.mqttproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    @Size(max = 40)
    private String content;

    @Size(max = 20)
    private String topic;

    private LocalDateTime created;

    public Message() {
    }

    public Message(String content, String topic, LocalDateTime created) {
        this.content = content;
        this.topic = topic;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
