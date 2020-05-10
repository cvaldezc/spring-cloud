package com.chris.learn.microservices.limitservice.bean;

import lombok.Getter;

@Getter
public class LimitConfiguration {

  private int maximun;
  private int minimun;

  protected LimitConfiguration() {

  }

  public LimitConfiguration(int maximun, int minimun) {
    super();
    this.maximun = maximun;
    this.minimun = minimun;
  }
}
