package com.example.Ninsho;
import com.example.Ninsho.entity.Auth;
import com.example.Ninsho.mapper.AuthManageMapper;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
@Component
public class ExpiredDateChecker {
    @Autowired
    AuthManageMapper authManageMapper;
    public boolean isExpired(String accessToken) {
        List<Auth> authList = authManageMapper.selectByPrimaryKey(accessToken);
        if (authList.size() == 0) {
            return false;
        }
        return true;
    }
}