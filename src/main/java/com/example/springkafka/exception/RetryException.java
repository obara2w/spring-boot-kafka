package com.example.springkafka.exception;

public class RetryException extends RuntimeException {
  public RetryException(String message) {
    super(message);
  }

  public RetryException() {
    super();
  }
}
