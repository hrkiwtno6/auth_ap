package com.example.Ninsho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
    //Serviceタグが付いているクラスをSpringが見つけてきて、勝手にnewしてくれる。
public class SearchInfoService {
    @Autowired
    private StorageInfoMapper storageInfoMapper;

    public ArrayList<PasswordInfo> exec(String groupId) {
        ArrayList<PasswordInfo> passwordInfoList = new ArrayList<>();
        passwordInfoList = storageInfoMapper.selectALL(groupId);
        return passwordInfoList;
    }
}