package com.currency.quotation.core.exceptions;

import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardException> objectNotFound(ObjectNotFoundException e) {
        StandardException err = new StandardException(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public BindErrorResponse bindError(BindException e) {
        List<FieldError> errorList = e.getBindingResult().getFieldErrors();
        List<BindErrorResponse.ErrorDetails> errorDetails = new ArrayList<>();
        for (FieldError err : errorList) {
            BindErrorResponse.ErrorDetails error = new BindErrorResponse.ErrorDetails();
            error.setField(err.getField());
            error.setError(err.getDefaultMessage());
            errorDetails.add(error);
        }
        BindErrorResponse bindErrorResponse = new BindErrorResponse();
        bindErrorResponse.setErrors(errorDetails);
        return bindErrorResponse;
    }

}
