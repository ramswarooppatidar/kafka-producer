package com.springboot.project.Kafka_consumer.service;

import org.apache.kafka.common.protocol.Message;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.springboot.project.Kafka_consumer.dto.Customer;

import ch.qos.logback.classic.Logger;

@Service
public class KafkaMesssageListner {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(KafkaMesssageListner.class);

	
	@KafkaListener(topics="custome-object",  groupId = "jt-custom-group-1", containerFactory = "kafkaListenerContainerFactory")
	public void consumeObject(Customer message) {
		logger.info("consumeObject message : {}",message);
	}
	
	@KafkaListener(topics="demo-topic-1", groupId = "jt-group-2")
	public void consume1(String message) {
		logger.info("consume1 message : {}"+message);
	}
	
	@KafkaListener(topics="demo-topic-1", groupId = "jt-group-2")
	public void consume2(String message) {
		logger.info("consume2 message : {}"+message);
	}
	@KafkaListener(topics="demo-topic-1", groupId = "jt-group-2")
	public void consume3(String message) {
		logger.info("consume3 message : {}"+message);
	}

	
	
}
