package com.example.springkafka.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springkafka.kafka.KafkaProducer;

@RestController
@RequestMapping("test")
public class TestController {

  @Autowired
  private KafkaProducer producer;
  
  @GetMapping
  public String getTest() {
    UUID uuid = UUID.randomUUID();
    producer.publish("test", uuid.toString());
    return "OK";
  }
}
