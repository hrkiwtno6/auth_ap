package com.example.Ninsho.controller.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class GetStorageInfoListInDto {
    private String groupId;
    public GetStorageInfoListInDto(JsonNode jsonNode) {
        this.groupId = jsonNode.get("groupId").textValue();
    }
    // Getters and Setters
    public String getGroupId() {
        return groupId;
    }
}