package com.example.Ninsho;

import org.apache.ibatis.annotations.*;

import java.time.Instant;
import java.util.List;

@Mapper
public interface UserMapper {
    @ConstructorArgs({
            @Arg(column = "GROUP_ID", javaType = String.class,name = "groupId"),
            @Arg(column = "USER_ID", javaType = String.class, id = true,name = "userId"),
            @Arg(column = "PASS", javaType = String.class, id = true,name = "pass")
    })
    @Select(
            "SELECT " +
                    "GROUP_ID, " +
                    "USER_ID, " +
                    "PASS " +
                    "FROM USERS " +
                    "WHERE USER_ID = #{userId} AND " +
                    "PASS = #{pass}"
    )
    List<User> selectByPrimaryKey(String userId, String pass);

    @Insert(
            "INSERT INTO USERS " +
                    "(GROUP_ID, USER_ID, PASS) " +
                    "VALUES " +
                    "(#{groupId}," +
                    " #{userId}," +
                    " #{pass}" +
                    ")"
    )
    void regist(String groupId, String userId, String pass);
}
