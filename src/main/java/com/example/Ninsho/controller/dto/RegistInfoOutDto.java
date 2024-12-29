package com.example.Ninsho.controller.dto;

public class RegistInfoOutDto {
    private final String storageInfoId;
    public RegistInfoOutDto(int storageInfoId) {
        this.storageInfoId = String.valueOf(storageInfoId);
    }
    public String getStorageInfoId() {
        return storageInfoId;
    }
}
