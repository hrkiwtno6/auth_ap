package com.example.Ninsho.entity;

public class Auth {
    private final int userId;
    private final String accessToken;

    private final String expiredDate;

    private Auth(int userId, String accessToken, String expiredDate) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.expiredDate = expiredDate;
    }

    public int getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getExpiredDate() {
        return expiredDate;
    }
}