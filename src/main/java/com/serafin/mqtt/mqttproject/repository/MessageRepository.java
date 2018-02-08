package com.serafin.mqtt.mqttproject.repository;

import com.serafin.mqtt.mqttproject.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findMessagesByTopic(String topic);
}
