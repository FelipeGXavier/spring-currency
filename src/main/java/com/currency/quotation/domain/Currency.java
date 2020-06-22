package com.currency.quotation.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Currency {

    private int id;
    private float quotation;
    private CurrencyType currency_type;


    @JsonIgnore
    public int getId() {
        return id;
    }

    public Currency setId(int id) {
        this.id = id;
        return this;
    }

    public float getQuotation() {
        return quotation;
    }

    public Currency setQuotation(float quotation) {
        this.quotation = quotation;
        return this;
    }

    public CurrencyType getCurrency_type() {
        return currency_type;
    }

    public Currency setCurrency_type(CurrencyType currency_type) {
        this.currency_type = currency_type;
        return this;
    }
}
