package com.example.Ninsho.controller.dto;

public class RegistStorageInfoOutDto {
    private final String storageInfoId;
    public RegistStorageInfoOutDto(int storageInfoId) {
        this.storageInfoId = String.valueOf(storageInfoId);
    }
    public String getStorageInfoId() {
        return storageInfoId;
    }
}
