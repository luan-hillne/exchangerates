package com.kodedge.port;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodedge.domain.ExchangeRate;
import com.kodedge.domain.client.ExchangeRateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRateClientImpl implements ExchangeRateClient {
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private ExchangeRateProperties exchangeRateProperties;


    @Autowired
    public ExchangeRateClientImpl(RestTemplate restTemplate, ObjectMapper objectMapper, ExchangeRateProperties exchangeRateProperties) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.exchangeRateProperties = exchangeRateProperties;
    }

    @Override
    public ExchangeRate getLatestRate(String currency) {
        String url = exchangeRateProperties.getUrl();
        try {
            String jsonResponse = restTemplate.getForObject(url, String.class);
            return objectMapper.readValue(jsonResponse, ExchangeRate.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
