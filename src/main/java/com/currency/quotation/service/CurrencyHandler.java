package com.currency.quotation.service;

import com.currency.quotation.core.HttpConnection;
import com.currency.quotation.domain.Currency;
import com.currency.quotation.domain.CurrencyType;
import com.currency.quotation.model.CurrencyModel;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CurrencyHandler {

    private final HttpConnection connection;
    private final CurrencyModel currencyModel;
    private final String BASE_REQUEST = "https://api.exchangeratesapi.io/latest?base=%s";

    @Autowired
    public CurrencyHandler(HttpConnection connection, CurrencyModel currencyModel) {
        this.connection = connection;
        this.currencyModel = currencyModel;
    }

    public void getLastCurrencyInBaseType() throws IOException {
        String requestUrl = String.format(BASE_REQUEST, "USD");
        String jsonString = this.connection.getUrl(requestUrl);
        JSONObject jsonObject = new JSONObject(jsonString);
        if (jsonObject.has("rates")) {
            Map<String, Object> mapRates = new JSONObject(jsonObject.get("rates").toString()).toMap();
            List<CurrencyType> list = this.currencyModel.getAllCurrencyTypes();
            HashMap<Integer, Double> mapCurrencyRates = new HashMap<>();
            mapRates.forEach((key, value) -> list.forEach(currencyType -> {
                if(key.equals(currencyType.getType())){
                    mapCurrencyRates.put(currencyType.getId(), Double.parseDouble(value.toString()));
                }
            }));
            this.currencyModel.create(mapCurrencyRates);
        }
    }



}
