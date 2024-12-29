package com.example.Ninsho.controller.dto;

import com.example.Ninsho.entity.PasswordInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class SearchInfoOutDto {
    private final JsonNode json;

    public SearchInfoOutDto(List<PasswordInfo> passwordInfoList) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        for (PasswordInfo info : passwordInfoList) {
            ObjectNode infoNode = mapper.createObjectNode();
            infoNode.put("groupId", info.getGroupId());
            infoNode.put("storageInfoId", info.getStorageInfoId());
            infoNode.put("storageInfoName", info.getStorageInfoName());
            infoNode.put("storageInfoPass", info.getStorageInfoPass());
            infoNode.put("storageInfoMemo", info.getStorageInfoMemo());
            arrayNode.add(infoNode);
        }

        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.set("passwordInfoList", arrayNode);
        this.json = rootNode;
    }

    public JsonNode getJson() {
        return json;
    }
}