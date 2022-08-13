package com.example.springkafka.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import com.example.springkafka.exception.RetryException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaConsumer {

  @Autowired
  private KafkaProducer producer;
  
  @Retryable(maxAttempts = 3,  value = RetryException.class, backoff = @Backoff(delay = 200, multiplier = 2))
  @KafkaListener(topics = "myTopic")
  public void processMessage(String message) {
    log.info("Message received: {}", message);

    if (message.equals("erro")) throw new RetryException("Tente novamente!");
    
    log.info("Sucesso!");
  }

  @Recover
  public void recover(RetryException e, String message) {
    String m = "recover !!!!!!!!!" + message;
    log.error(m);
    producer.publish("myTopic.dlq", message);
  }

}
