package com.kodedge.rest;

import com.kodedge.domain.ExchangeRate;
import com.kodedge.domain.ExchangeRatesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeRatesController {

    ExchangeRatesService exchangeRatesService;

    public ExchangeRatesController(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @GetMapping("/latest/{currency}")
    public ExchangeRate getData(@PathVariable String currency) {

        return exchangeRatesService.getExchangeRate(currency);
    }

}
