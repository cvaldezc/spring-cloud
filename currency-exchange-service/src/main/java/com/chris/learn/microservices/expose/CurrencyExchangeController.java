package com.chris.learn.microservices.expose;

import com.chris.learn.microservices.currencyexchangeservice.ExchangeValue;
import com.chris.learn.microservices.currencyexchangeservice.ExchangeValueRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {

  private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

  private final Environment environment;

  private final ExchangeValueRepository repository;

  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
    ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
    logger.info("ExchangeValue -> {} ", exchangeValue);
    exchangeValue.setPort(Integer.valueOf(environment.getProperty("local.server.port")));
    return exchangeValue;
  }
}
