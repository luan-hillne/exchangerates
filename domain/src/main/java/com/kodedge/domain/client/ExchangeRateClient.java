package com.kodedge.domain.client;

import com.kodedge.domain.ExchangeRate;

public interface ExchangeRateClient {
    ExchangeRate getLatestRate(String currency);
}
