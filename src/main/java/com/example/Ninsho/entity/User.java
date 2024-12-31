package com.example.Ninsho.entity;

public class User { ;
    private final int userId;
    private final String loginId;
    private final String loginPw;

    public User(int userId,String loginId, String loginPw) {
        this.userId = userId;
        this.loginId = loginId;
        this.loginPw = loginPw;
    }

    public int getUserId() {
        return userId;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getLoginPw() {
        return loginPw;
    }
}