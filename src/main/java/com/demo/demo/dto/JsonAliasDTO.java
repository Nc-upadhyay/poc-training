package com.demo.demo.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class JsonAliasDTO {
    @JsonProperty("userName")
    @JsonAlias({"userName","fullName","name"})
    private String name;
    private String password;
    private String mobile;

    public JsonAliasDTO(String name, String password, String mobile) {
        this.name = name;
        this.password = password;
        this.mobile = mobile;
    }
}
