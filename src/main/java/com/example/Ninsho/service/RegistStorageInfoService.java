package com.example.Ninsho.service;

import com.example.Ninsho.mapper.StorageInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    //Serviceタグが付いているクラスをSpringが見つけてきて、勝手にnewしてくれる。
public class RegistStorageInfoService {
    @Autowired
    private StorageInfoMapper storageInfoMapper;
    public void registStrageInfo(String groupId, String storageInfoId, String storageInfoName, String storageInfoPass, String stratgeInfoMemo) {
        storageInfoMapper.regist(groupId, storageInfoId, storageInfoName, storageInfoPass, stratgeInfoMemo);
    }
}