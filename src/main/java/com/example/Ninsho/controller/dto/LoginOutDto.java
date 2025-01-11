package com.example.Ninsho.controller.dto;

public class LoginOutDto {
    private final String userId;

    public LoginOutDto(int userId) {
        this.userId = String.valueOf(userId);
    }
    public String getUserId() {
        return userId;
    }
}
