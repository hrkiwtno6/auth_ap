package com.example.Ninsho.controller.dto;

public class DeleteStorageInfoOutDto {
    private final String storageInfoId;
    public DeleteStorageInfoOutDto(int storageInfoId) {
        this.storageInfoId = String.valueOf(storageInfoId);
    }
    public String getStorageInfoId() {
        return storageInfoId;
    }
}
