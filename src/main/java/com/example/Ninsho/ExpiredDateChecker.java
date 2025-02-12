package com.example.Ninsho;
import com.example.Ninsho.entity.Auth;
import com.example.Ninsho.mapper.AuthManageMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

public class ExpiredDateChecker {
    @Autowired
    AuthManageMapper authManageMapper;
    public boolean isExpired(int userId) {
        List<Auth> authList = authManageMapper.selectByPrimaryKey(userId);
        if (authList.size() == 0) {
            return false;
        }
        return true;
    }
}