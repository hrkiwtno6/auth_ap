package com.example.Ninsho.entity;

public class StorageInfo {
    public StorageInfo(String groupId, String storageInfoId, String storageInfoName) {
        this.groupId = groupId;
        this.storageInfoId = storageInfoId;
        this.storageInfoName = storageInfoName;
    }
    private String groupId;
    private String storageInfoId;
    private String storageInfoName;
}