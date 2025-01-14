package com.example.Ninsho.controller.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class LoginInDto {
    private final String loginId;
    private final String loginPw;

    public LoginInDto(JsonNode jsonNode) {
        this.loginId = jsonNode.get("loginId").textValue();
        this.loginPw = jsonNode.get("loginPw").textValue();
    }


    public String getLoginPw() {
        return loginPw;
    }

    public String getLoginId() {
        return loginId;
    }
}