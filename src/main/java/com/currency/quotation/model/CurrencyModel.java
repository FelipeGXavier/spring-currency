package com.currency.quotation.model;

import com.currency.quotation.core.CurrencyRowMapper;
import com.currency.quotation.core.ICurrency;
import com.currency.quotation.core.exceptions.ObjectNotFoundException;
import com.currency.quotation.domain.Currency;
import com.currency.quotation.domain.CurrencyType;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

@Repository
public class CurrencyModel implements ICurrency {


    private final NamedParameterJdbcTemplate template;

    public CurrencyModel(DataSource source) {
        this.template = new NamedParameterJdbcTemplate(source);
    }

    @Override
    public void create(HashMap<Integer, Double> mapCurrencies) {
        String sql = "INSERT INTO currency (value, type_id) values (:value, :type_id) ON CONFLICT ON CONSTRAINT type_id_fk DO update set value = :value";
        mapCurrencies.entrySet().forEach(entry -> {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("value", entry.getValue())
                    .addValue("type_id", entry.getKey());
            this.template.update(sql, parameterSource);
        });
    }

    public List<CurrencyType> getAllCurrencyTypes() {
        String sql = "SELECT * from currency_type";
        return this.template.query(sql, new BeanPropertyRowMapper<>(CurrencyType.class));
    }

    public Currency getCurrencyByType(String type) {
        String sql = "select t.type as type, t.symbol, c.value as quotation  from currency c\n" +
                "inner join currency_type t on c.type_id = t.id\n" +
                "where t.type = :type";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("type", type.toUpperCase());
        try{
            Currency currency = this.template.queryForObject(sql, parameterSource, new CurrencyRowMapper());
            return currency;
        }catch (Exception e){
            throw new ObjectNotFoundException("Nenhum câmbio foi encontrado para esta requisição");
        }

    }

    public List<Currency> getAllCurrencies() {
        String sql = "select t.type as type, t.symbol, c.value as quotation  from currency c\n" +
                "inner join currency_type t on c.type_id = t.id\n" +
                "order by t.id";
        return this.template.query(sql, new CurrencyRowMapper());
    }


}
