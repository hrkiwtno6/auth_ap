package com.example.Ninsho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    //Serviceタグが付いているクラスをSpringが見つけてきて、勝手にnewしてくれる。
public class NinshoService {
    @Autowired
    private UserMapper userMapper;
    public String certificete(String userId, String pass) {
        String loginStatus = null;
        try {
            if (userMapper.selectByPrimaryKey(userId, pass).size() == 1) {
                loginStatus = NinshoConstants.LOGIN_SUCCESSFUL;
                System.out.println("sucsess"+"loginsrtatus:"+loginStatus);
            } else {
                loginStatus = NinshoConstants.LOGIN_FAILURE;
                System.out.println("failure"+"loginsrtatus:"+loginStatus);
                if (userMapper.selectByPrimaryKey(userId, pass).size() == 0) {
                    return loginStatus;
                    //TODO ここでthrowするときにステータス値をしていしてthrowしたい。
                } else {
                    System.out.println("syserror");
                    throw new RuntimeException("システムエラー");
                }
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            //TODO なんかいい感じにエラーとして処理したい。
        }
        System.out.println("loginsrtatus:"+loginStatus);
        return loginStatus;
    }
}