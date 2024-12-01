package com.example.Ninsho;

public class PasswordInfo {
    private final String groupId;
    private final String storageInfoId;
    private final String storageInfoName;
    private final String storageInfoPass;
    private final String storageInfoMemo;

    public PasswordInfo(String groupId, String storageInfoId, String storageInfoName, String storageInfoPass, String storageInfoMemo) {
        this.groupId = groupId;
        this.storageInfoId = storageInfoId;
        this.storageInfoName = storageInfoName;
        this.storageInfoPass = storageInfoPass;
        this.storageInfoMemo = storageInfoMemo;
    }

    public String getGroupId() {
        return groupId;
    }
    public String getStorageInfoId() {
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