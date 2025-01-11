package com.example.Ninsho.service;

import com.example.Ninsho.mapper.UserMapper;
import com.example.Ninsho.utils.CryptoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
    //Serviceタグが付いているクラスをSpringが見つけてきて、勝手にnewしてくれる。
public class RegistUserService {
    @Autowired
    private UserMapper userMapper;
    public int exec(String loginId, String loginPw) {
        int userId = userMapper.generateSeq();
//        String encloginPw = CryptoData.encrypt(loginPw,"keyabcd");
          String encloginPw ="abcd";
        userMapper.regist(userId, loginId, encloginPw);
        return userId;
    }
}