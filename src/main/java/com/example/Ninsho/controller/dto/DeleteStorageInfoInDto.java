package com.example.Ninsho.controller.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class DeleteStorageInfoInDto {
    private final String groupId;
    private final String storageInfoId;

    public DeleteStorageInfoInDto(String groupId, String userId, String storageInfoId) {
        this.groupId = groupId;
        this.storageInfoId = storageInfoId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getStorageInfoId() {
        return storageInfoId;
    }
}
