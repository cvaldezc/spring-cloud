package com.chris.learn.microservices.expose;

import com.chris.learn.microservices.limitservice.Configuration;
import com.chris.learn.microservices.limitservice.bean.LimitConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LimitsConfigurationController {

  private final Configuration configuration;

  @GetMapping("/limits")
  public LimitConfiguration retrieveLimitsFromConfigurations() {
    return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());
  }

}
