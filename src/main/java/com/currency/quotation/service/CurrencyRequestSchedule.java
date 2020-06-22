package com.currency.quotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@EnableScheduling
public class CurrencyRequestSchedule {

    private final long SECOND = 1000;
    private final long MINUTE = SECOND * 30;
    private final long HOUR = MINUTE * 1;
    private final CurrencyHandler currencyHandler;

    @Autowired
    public CurrencyRequestSchedule(CurrencyHandler currencyHandler){
        this.currencyHandler = currencyHandler;
    }

    @Scheduled(fixedDelay = HOUR)
    public void requestNewCurrencyQuotation() throws IOException {
        System.out.println("Starting schedule...");
        this.currencyHandler.getLastCurrencyInBaseType();
    }
}
