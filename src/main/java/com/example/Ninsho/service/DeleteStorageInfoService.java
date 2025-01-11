package com.example.Ninsho.service;

import com.example.Ninsho.mapper.StorageInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    //Serviceタグが付いているクラスをSpringが見つけてきて、勝手にnewしてくれる。
public class DeleteStorageInfoService {
    @Autowired
    private StorageInfoMapper storageInfoMapper;
    public int exec(String groupId, int storageInfoId) {
        int result = storageInfoMapper.softDelete(groupId, storageInfoId);
        if (result != 1) {
            throw new RuntimeException("Failed to softDelete storageInfo.");
        }
        return storageInfoId;
    }
}