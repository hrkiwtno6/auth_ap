package com.example.Ninsho.service;

import com.example.Ninsho.RandomGenerator;
import com.example.Ninsho.entity.Auth;
import com.example.Ninsho.entity.User;
import com.example.Ninsho.mapper.AuthManageMapper;
import com.example.Ninsho.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//Serviceタグが付いているクラスをSpringが見つけてきて、勝手にnewしてくれる。
public class NinshoService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthManageMapper authManageMapper;

    public class ExecOutDto {
        //下記にaccessTokenとuserIdを返す処理を書く。
        public final String accessToken;
        public final int userId;

        ExecOutDto(String accessToken, int userId) {
            this.accessToken = accessToken;
            this.userId = userId;
        }
    }

    public ExecOutDto exec(String liginId, String liginPw) {
        String loginStatus = null;
        String accessToken = null;
        int userId = 0;
        try {
            List<User> userList = userMapper.selectByPrimaryKey(liginId, liginPw);
            if (userList.size() == 0) {
                System.out.println("ログイン失敗");
                //TODO　Exceptionハンドラー
                loginStatus = "NG";
            } else if (userList.size() == 1) {
                loginStatus = "OK";
                User autholizedUser = userList.getFirst();
            }
            userId = userList.getFirst().getUserId();
            List<Auth> authList = authManageMapper.selectByPrimaryKey(userId);
            if (authList.size() == 0) {
                accessToken = RandomGenerator.generateRandom(64);
                int resuleRegist = authManageMapper.regist(userId, accessToken);
                if (resuleRegist != 1) {
                //TODO　Exceptionハンドラー
                    System.out.println("登録失敗");
                }
            } else if (authList.size() != 1) {
                //TODO　Exceptionハンドラー
                System.out.println("複数のアクセストークンが存在します。");
            } else {
                accessToken = authList.getFirst().getAccessToken();
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            //TODO なんかいい感じにエラーとして処理したい。
        }
        ExecOutDto execOutDto = new ExecOutDto(accessToken, userId);
        return execOutDto;
    }
}