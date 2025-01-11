package com.example.Ninsho.service;

import com.example.Ninsho.entity.User;
import com.example.Ninsho.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    //Serviceタグが付いているクラスをSpringが見つけてきて、勝手にnewしてくれる。
public class NinshoService {
    @Autowired
    private UserMapper userMapper;
    public int exec(String liginId, String liginPw) {
        String loginStatus = null;
        int userId = 0;
        try {
            List<User> userList = userMapper.selectByPrimaryKey(liginId, liginPw);
            if (userList.size() == 0) {
                System.out.println("ログイン失敗");
                loginStatus = "NG";
            } else if(userList.size() == 1){
                loginStatus = "OK";
                User autholizedUser = userList.getFirst();
            }
            userId = userList.getFirst().getUserId();
        }catch (RuntimeException e) {
            System.out.println(e.getMessage());
            //TODO なんかいい感じにエラーとして処理したい。
        }
        return userId;
    }
}