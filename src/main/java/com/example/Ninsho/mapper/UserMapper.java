package com.example.Ninsho.mapper;

import com.example.Ninsho.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @ConstructorArgs({
            @Arg(column = "USER_ID", javaType = int.class, id = true,name = "userId"),
            @Arg(column = "LOGIN_ID", javaType = String.class, name = "loginId"),
            @Arg(column = "LOGIN_PW", javaType = String.class, name = "loginPw")
    })
    @Select(
            "SELECT " +
                    "USER_ID, " +
                    "LOGIN_ID, " +
                    "LOGIN_PW " +
                    "FROM MA_USER " +
                    "WHERE LOGIN_ID = #{loginId} AND " +
                    "LOGIN_PW = #{loginPw}"
    )
    List<User> selectByPrimaryKey(String loginId, String loginPw);

    @Insert(
            "INSERT INTO USERS " +
                    "(USER_ID, LOGIN_ID, LOGIN_PW) " +
                    "VALUES " +
                    "(" +
                    " #{userId}," +
                    " #{loginId}," +
                    " #{loginPw}" +
                    ")"
    )
    void regist(int userId, String loginId, String loginPw);
    @Select(
            "SELECT nextval('user_id_seq');"
    )
    int generateSeq();
}
