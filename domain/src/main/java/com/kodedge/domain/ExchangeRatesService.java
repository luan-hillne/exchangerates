package com.kodedge.domain;

import com.kodedge.domain.client.ExchangeRateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRatesService {

    private ExchangeRateClient exchangeRateClient;

    @Autowired
    public ExchangeRatesService(ExchangeRateClient exchangeRateClient) {
        this.exchangeRateClient = exchangeRateClient;
    }

    public ExchangeRate getExchangeRate(String baseCurrency) {
        return exchangeRateClient.getLatestRate(baseCurrency);
    }
}
