package com.example.Ninsho.service;

import com.example.Ninsho.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    //Serviceタグが付いているクラスをSpringが見つけてきて、勝手にnewしてくれる。
public class RegistUserService {
    @Autowired
    private UserMapper userMapper;
    public int exec(String loginId, String loginPw) {
        int userId = userMapper.generateSeq();
        userMapper.regist(userId, loginId, loginPw);
        return userId;
    }
}