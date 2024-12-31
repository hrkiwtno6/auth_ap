package com.example.Ninsho.controller.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class SearchInfoInDto {
    private String groupId;
    public SearchInfoInDto(JsonNode jsonNode) {
        this.groupId = jsonNode.get("groupId").textValue();
    }
    // Getters and Setters
    public String getGroupId() {
        return groupId;
    }
}