package com.kodedge.domain;

import com.kodedge.domain.client.ExchangeRateClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExchangeRatesServiceTest {

    @Mock
    private ExchangeRateClient exchangeRateClient;

    @InjectMocks
    private ExchangeRatesService exchangeRatesService;

    @Test
    public void should_return_exchange_rate() {
        // Arrange
        //Thread.sleep(1);

        String baseCurrency = "USD";
        ExchangeRate expectedExchangeRate = getExchangeRate();

        when(exchangeRateClient.getLatestRate(baseCurrency)).thenReturn(expectedExchangeRate);

        // Act
        ExchangeRate actualRate = exchangeRatesService.getExchangeRate(baseCurrency);

        // Assert
        assertEquals(expectedExchangeRate.getBaseCurrency(), actualRate.getBaseCurrency());
        assertEquals(expectedExchangeRate.getRates(), actualRate.getRates());
    }

    public ExchangeRate getExchangeRate() {
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("AED", 3.6725);
        rates.put("JPY", 153.232404);
        rates.put("INR", 84.109224);
        return new ExchangeRate("USD", rates);
    }

}