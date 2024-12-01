package com.example.Ninsho;

import com.fasterxml.jackson.databind.JsonNode;

public class RegistInfoInDto {
    private final String groupId;
    private final String storageInfoId;
    private final String storageInfoName;
    private final String storageInfoPass;
    private final String storageInfoMemo;
    public RegistInfoInDto(JsonNode jsonNode) {
        this.groupId = jsonNode.get("groupId").textValue();
        this.storageInfoId = jsonNode.get("storageInfoId").textValue();
        this.storageInfoName = jsonNode.get("storageInfoName").textValue();
        this.storageInfoPass = jsonNode.get("storageInfoPass").textValue();
        this.storageInfoMemo = jsonNode.get("storageInfoMemo").textValue();
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
