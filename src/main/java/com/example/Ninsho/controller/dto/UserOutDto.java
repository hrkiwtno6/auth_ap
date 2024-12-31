package com.example.Ninsho.controller.dto;

public class UserOutDto {
    private final String userId;

    public UserOutDto(int userId) {
        this.userId = String.valueOf(userId);
    }
    public String getUserId() {
        return userId;
    }
}
