package com.currency.quotation.rest;

import com.currency.quotation.domain.Currency;
import com.currency.quotation.domain.CurrencyType;
import com.currency.quotation.domain.TestDTO;
import com.currency.quotation.model.CurrencyModel;
import com.currency.quotation.service.CurrencyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/currency")
public class CurrencyController {


    private final CurrencyModel currencyModel;

    @Autowired
    public CurrencyController(CurrencyHandler handler, CurrencyModel currencyModel) {
        this.currencyModel = currencyModel;
    }


    @GetMapping("")
    public ResponseEntity<List<Currency>> getActualCurrency() {
        List<Currency> currencyList = this.currencyModel.getAllCurrencies();
        if (currencyList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(currencyList, HttpStatus.OK);
        }
    }

    @GetMapping("/types")
    public ResponseEntity<List<CurrencyType>> getAllCurrenciesType() {
        List<CurrencyType> currencyTypeList = this.currencyModel.getAllCurrencyTypes();
        if (currencyTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(currencyTypeList, HttpStatus.OK);
        }
    }

    @GetMapping("/{type}")
    public ResponseEntity<Currency> getActualCurrencyByType(@PathVariable String type) {
        return new ResponseEntity<>(this.currencyModel.getCurrencyByType(type), HttpStatus.OK);
    }

    @PostMapping(value = "/dummy")
    public ResponseEntity<?> dummy(@Valid @ModelAttribute TestDTO testDTO, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return null;

    }

    /*@InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new CurrencyValidator());
    }*/

    /*@ExceptionHandler(ObjectNotFoundException.class)
    public StandardException dummy(){
        return new StandardException(500,"dummy", System.currentTimeMillis());
    }*/

}
