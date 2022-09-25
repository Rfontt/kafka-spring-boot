package com.microservice.kafka.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserEntity {
    private String name;
    private String email;

    public UserEntity() {}

    public UserEntity(
            @JsonProperty String name,
            @JsonProperty String email
    ) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
