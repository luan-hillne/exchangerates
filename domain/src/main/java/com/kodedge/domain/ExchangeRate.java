package com.kodedge.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

public class ExchangeRate {
    @JsonProperty("base")
    private String baseCurrency;
    @JsonProperty("rates")
    private Map<String, Double> rates;
    @JsonProperty("last_updated")
    private Date lastUpdated;

    public ExchangeRate() {}

    public ExchangeRate(String baseCurrency, Map<String, Double> rates) {
        this.baseCurrency = baseCurrency;
        this.rates = rates;
        this.lastUpdated = new Date();
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "baseCurrency='" + baseCurrency + '\'' +
                ", rates=" + rates +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}

