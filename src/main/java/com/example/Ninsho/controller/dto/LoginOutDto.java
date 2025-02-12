package com.example.Ninsho.controller.dto;

public class LoginOutDto {
    private final String userId;
    private final String accessToken;

    public LoginOutDto(int userId, String accessToken) {
        this.userId = String.valueOf(userId);
        this.accessToken = accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
