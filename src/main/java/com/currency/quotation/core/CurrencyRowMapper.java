package com.currency.quotation.core;

import com.currency.quotation.domain.Currency;
import com.currency.quotation.domain.CurrencyType;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyRowMapper implements RowMapper<Currency> {

    @Override
    public Currency mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        CurrencyType currencyType = (new BeanPropertyRowMapper<>(CurrencyType.class)).mapRow(resultSet,rowNum);
        Currency currency = (new BeanPropertyRowMapper<>(Currency.class)).mapRow(resultSet,rowNum);
        currency.setCurrency_type(currencyType);
        return currency;
    }
}
