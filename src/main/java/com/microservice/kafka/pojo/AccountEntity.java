package com.microservice.kafka.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountEntity {
    private String holder;
    private String funds;

    public AccountEntity() {}

    public AccountEntity(
        @JsonProperty String holder,
        @JsonProperty String funds
    ) {
        this.holder = holder;
        this.funds = funds;
    }

    public String getHolder() {
        return holder;
    }

    public String getFunds() {
        return funds;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }
}
