package com.example.Ninsho.controller.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class DeleteStorageInfoInDto {
    private final String groupId;
    private final String storageInfoId;

    public DeleteStorageInfoInDto(JsonNode jsonNode) {
        this.groupId = jsonNode.get("groupId").textValue();
        this.storageInfoId = jsonNode.get("storageInfoId").textValue();
    }

    public String getGroupId() {
        return groupId;
    }

    public String getStorageInfoId() {
        return storageInfoId;
    }
}
