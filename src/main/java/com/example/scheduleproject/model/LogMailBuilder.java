package com.example.scheduleproject.model;

import java.time.LocalDateTime;

public class LogMailBuilder {
    private String productName;
    private String userEmail;
    private String status;


    public LogMailBuilder setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public LogMailBuilder setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public LogMailBuilder setStatus(String status) {
        this.status = status;
        return this;
    }


    public static LogMailBuilder builder() {
        return new LogMailBuilder();
    }

    public LogEmail build() {
        return new LogEmail(productName, userEmail, status);
    }
}
