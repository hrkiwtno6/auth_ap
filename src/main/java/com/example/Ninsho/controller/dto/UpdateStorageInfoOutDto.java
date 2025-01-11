package com.example.Ninsho.controller.dto;

public class UpdateStorageInfoOutDto {
    private final String storageInfoId;
    public UpdateStorageInfoOutDto(int storageInfoId) {
        this.storageInfoId = String.valueOf(storageInfoId);
    }
    public String getStorageInfoId() {
        return storageInfoId;
    }
}
