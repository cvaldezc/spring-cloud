package com.chris.learn.microservices.limitservice.bean;

import lombok.Getter;

@Getter
public class LimitConfiguration {

  private int maximum;
  private int minimum;

  protected LimitConfiguration() {

  }

  public LimitConfiguration(int maximum, int minimun) {
    super();
    this.maximum = maximum;
    this.minimum = minimun;
  }
}
