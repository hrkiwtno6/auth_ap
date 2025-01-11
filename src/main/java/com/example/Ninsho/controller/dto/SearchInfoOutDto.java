package com.example.Ninsho.controller.dto;

import com.example.Ninsho.entity.StorageInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class SearchInfoOutDto {
    private final JsonNode json;

    public SearchInfoOutDto(List<StorageInfo> storageInfoList) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNode = objectMapper.createArrayNode();

        for (StorageInfo storageInfo : storageInfoList) {
            ObjectNode storageInfoNode = objectMapper.createObjectNode();
            storageInfoNode.put("groupId", storageInfo.getGroupId().toString());
            storageInfoNode.put("storageInfoId", storageInfo.getStorageInfoId());
            storageInfoNode.put("storageInfoName", storageInfo.getStorageInfoName());
            storageInfoNode.put("storageInfoPass", storageInfo.getStorageInfoPass());
            storageInfoNode.put("storageInfoMemo", storageInfo.getStorageInfoMemo());
            arrayNode.add(storageInfoNode);
        }

        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.set("storageInfoList", arrayNode);
        this.json = rootNode;
    }

    public JsonNode getJson() {
        return json;
    }
}