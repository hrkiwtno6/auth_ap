package com.example.Ninsho.entity;

public class StorageInfo {
    private final String groupId;
    private final int storageInfoId;
    private final String storageInfoName;
    private final String storageInfoPass;
    private final String storageInfoMemo;

    private StorageInfo(String groupId, int storageInfoId, String storageInfoName, String storageInfoPass, String storageInfoMemo) {
        this.groupId = groupId;
        this.storageInfoId = storageInfoId;
        this.storageInfoName = storageInfoName;
        this.storageInfoPass = storageInfoPass;
        this.storageInfoMemo = storageInfoMemo;
    }

    public String getGroupId() {
        return groupId;
    }
    public int getStorageInfoId() {
        return storageInfoId;
    }
    public String getStorageInfoName() {
        return storageInfoName;
    }
    public String getStorageInfoPass() {
        return storageInfoPass;
    }
    public String getStorageInfoMemo() {
        return storageInfoMemo;
    }
}