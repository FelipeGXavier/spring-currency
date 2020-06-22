package com.currency.quotation.core;

import com.currency.quotation.domain.Currency;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CurrencyValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Currency.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Currency currency = (Currency) object;
        System.out.println("Testing..");
        if(currency.getQuotation() == 0){
            errors.reject("quotation", null, "dummy test");
        }
    }
}
