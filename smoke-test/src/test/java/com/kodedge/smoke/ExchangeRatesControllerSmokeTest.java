package com.kodedge.smoke;

import com.kodedge.app.ExchangeRatesApplication;
import com.kodedge.domain.ExchangeRate;
import com.kodedge.domain.client.ExchangeRateClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ExchangeRatesApplication.class
)
@ActiveProfiles("test")
public class ExchangeRatesControllerSmokeTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ExchangeRateClient exchangeRateClient;

    @Test
    void should_return_status_code_200_ok_response_for_application_health_api() {

        //Act
        ResponseEntity<String> response =
                restTemplate.getForEntity("/actuator/health", String.class);

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("\"status\":\"UP\""));
    }

    @Test
    void should_return_latest_exchange_rate_for_currency() {

        //Arrange
        String baseCurrency = "USD";
        when(exchangeRateClient.getLatestRate(any())).thenReturn(getExchangeRate());

        //Act
        ResponseEntity<ExchangeRate> response =
                restTemplate.getForEntity("/latest/{currency}",
                        ExchangeRate.class, baseCurrency);

        //Assert
        ExchangeRate exchangeRate = response.getBody();
        assertThat(exchangeRate).isNotNull();
        assertThat(exchangeRate.getBaseCurrency()).isEqualTo(baseCurrency);
        assertThat(exchangeRate.getRates()).isNotEmpty();
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
