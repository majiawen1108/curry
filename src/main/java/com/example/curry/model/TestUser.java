package com.example.curry.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author jw.ma
 * @title: TestUser
 * @description: TODO
 * @date 2022/4/15 10:17
 */
@Data
public class TestUser {
    @JsonProperty(value = "id")
    private String id;
    @JsonProperty(value = "username")
    private String username;
    @JsonProperty(value = "phoneNumber")
    private String phoneNumber;
    @JsonProperty(value = "address")
    private String address;
}
