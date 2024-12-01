package com.example.Ninsho;

public class User {
    public User(String groupId,String userId, String pass) {
        this.groupId = groupId;
        this.userId = userId;
        this.pass = pass;
    }
    private String userId;
    private String pass;
    private String groupId;
}