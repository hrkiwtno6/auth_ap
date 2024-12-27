package com.example.Ninsho.service;

import com.example.Ninsho.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    //Serviceタグが付いているクラスをSpringが見つけてきて、勝手にnewしてくれる。
public class RegistUserService {
    @Autowired
    private UserMapper userMapper;
    public void registUser(String groupId, String id, String pass) {
        userMapper.regist(groupId, id, pass);
    }
}