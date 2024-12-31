package com.example.Ninsho.service;

import com.example.Ninsho.entity.StorageInfo;
import com.example.Ninsho.mapper.StorageInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
    //Serviceタグが付いているクラスをSpringが見つけてきて、勝手にnewしてくれる。
public class SearchInfoService {
    @Autowired
    private StorageInfoMapper storageInfoMapper;

    public ArrayList<StorageInfo> exec(String groupId) {
        ArrayList<StorageInfo> storageInfoList = new ArrayList<>();
        storageInfoList = storageInfoMapper.selectALL(groupId);
        return storageInfoList;
    }
}