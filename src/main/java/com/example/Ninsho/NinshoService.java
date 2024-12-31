package com.example.Ninsho;

import com.example.Ninsho.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    //Serviceタグが付いているクラスをSpringが見つけてきて、勝手にnewしてくれる。
public class NinshoService {
    @Autowired
    private UserMapper userMapper;
    public String exec(String userId, String liginPw) {
        String loginStatus = null;
        try {
            if (userMapper.selectByPrimaryKey(userId, liginPw).size() == 1) {
                loginStatus = NinshoConstants.LOGIN_SUCCESSFUL;
            } else {
                loginStatus = NinshoConstants.LOGIN_FAILURE;
                if (userMapper.selectByPrimaryKey(userId, liginPw).size() == 0) {
                    return loginStatus;
                    //TODO ここでthrowするときにステータス値をしていしてthrowしたい。
                } else {
                    System.out.println("syserror");
                    throw new RuntimeException("システムエラー");
                }
            }
        }catch (RuntimeException e) {
            System.out.println(e.getMessage());
            //TODO なんかいい感じにエラーとして処理したい。
        }
        return loginStatus;
    }
}