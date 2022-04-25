package com.example.curry.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jw.ma
 * @title: TestUser
 * @description: @Data 和 @Builder 共存的话需要加入 @AllArgsConstructor 和 @NoArgsConstructor 注解
 * @date 2022/4/15 10:17
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
