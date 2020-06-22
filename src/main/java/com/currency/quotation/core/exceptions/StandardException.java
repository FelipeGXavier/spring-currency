package com.currency.quotation.core.exceptions;

public class StandardException {

    private Integer status;
    private String message;
    private Long timestamp;

    public StandardException(int value, String message, long currentTimeMillis) {
        this.status = value;
        this.message = message;
        this.timestamp = currentTimeMillis;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
