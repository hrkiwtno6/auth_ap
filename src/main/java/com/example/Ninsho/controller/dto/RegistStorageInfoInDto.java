package com.example.Ninsho.controller.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class RegistStorageInfoInDto {
    private final String groupId;
    private final String storageInfoName;
    private final String storageInfoPass;
    private final String storageInfoMemo;
    public RegistStorageInfoInDto(JsonNode jsonNode) {
        this.groupId = jsonNode.get("groupId").textValue();
        this.storageInfoName = jsonNode.get("storageInfoName").textValue();
        this.storageInfoPass = jsonNode.get("storageInfoPass").textValue();
        this.storageInfoMemo = jsonNode.get("storageInfoMemo").textValue();
    }

    public String getGroupId() {
        return groupId;
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
