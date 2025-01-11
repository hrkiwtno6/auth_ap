package com.example.Ninsho.service;

import com.example.Ninsho.mapper.StorageInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    //Serviceタグが付いているクラスをSpringが見つけてきて、勝手にnewしてくれる。
public class DeleteStorageInfoService {
    @Autowired
    private StorageInfoMapper storageInfoMapper;
    public int exec(String groupId, int storageInfoId, String storageInfoName, String storageInfoPass, String stratgeInfoMemo) {
        int result = storageInfoMapper.update(groupId, storageInfoId, storageInfoName, storageInfoPass, stratgeInfoMemo);
        if (result != 1) {
            throw new RuntimeException("Failed to update storageInfo.");
        }
        return storageInfoId;
    }
}