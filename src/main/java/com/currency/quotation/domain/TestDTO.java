package com.currency.quotation.domain;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TestDTO {

    @NotNull
    @Size(min = 3, max = 100)
    private String message;
    @NotNull
    @Email
    private String email;

    public TestDTO(String message,  String email) {
        this.message = message;
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

}
