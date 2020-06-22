package com.currency.quotation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CurrencyType {

    private String type;
    private int id;
    private String symbol;


    public String getType() {
        return type;
    }

    public CurrencyType setType(String type) {
        this.type = type;
        return this;
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    public CurrencyType setId(int id) {
        this.id = id;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public CurrencyType setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }
}
