package com.chris.learn.microservices.expose;

import com.chris.learn.microservices.currencyconversionservice.CurrencyConversionBean;
import com.chris.learn.microservices.currencyconversionservice.CurrencyExchangeServiceProxy;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class CurrencyConversionController {

  private final CurrencyExchangeServiceProxy proxy;

  @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversionBean convertCurrency(
      @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

    // Feign - Problem 1
    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("from", from);
    uriVariables.put("to", to);

    ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate()
        .getForEntity(
            "http://localhost:8001/currency-exchange/from/{from}/to/{to}",
            CurrencyConversionBean.class,
            uriVariables);

    CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
    return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
              quantity.multiply(response.getConversionMultiple()), response.getPort());
  }


  @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversionBean convertCurrencyFeign(
            @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
    CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
    return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
              quantity.multiply(response.getConversionMultiple()), response.getPort());
  }
}
