package com.serafin.mqtt.mqttproject.simple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findByTopic(String topic);

    List<Message> findMessagesByTopic(String topic);
}
