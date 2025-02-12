package com.example.Ninsho.mapper;

import com.example.Ninsho.entity.Auth;
import com.example.Ninsho.entity.StorageInfo;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface AuthManageMapper {
    @ConstructorArgs({
            @Arg(column = "USER_ID", javaType = int.class, id = true,name = "userId"),
            @Arg(column = "ACCESS_TOKEN", javaType = String.class,name = "accessToken"),
            @Arg(column = "EXPIRED_DATE", javaType = String.class,name = "expiredDate")
    })
    @Select(
            "SELECT " +
                    "USER_ID, " +
                    "ACCESS_TOKEN, " +
                    "EXPIRED_DATE " +
                    "FROM TR_AUTH_MANAGE " +
                    "WHERE " +
                    "USER_ID = #{userId} AND " +
                    "EXPIRED_DATE > CURRENT_TIMESTAMP"
    )
    ArrayList<Auth> selectByPrimaryKey(int userId);

    @Insert(
            "INSERT INTO TR_AUTH_MANAGE " +
                    "(USER_ID, ACCESS_TOKEN, EXPIRED_DATE) " +
                    "VALUES " +
                    "(#{userId}," +
                    " #{accessToken}," +
                    " CURRENT_TIMESTAMP + INTERVAL '30 minutes')"
    )
    int regist(int userId, String accessToken);

    @Update(
            "UPDATE TR_STORAGE_INFO_MANAGE " +
                    "SET " +
                    "STORAGE_INFO_NAME = #{storageInfoName}, " +
                    "STORAGE_INFO_PASS = #{storageInfoPass}, " +
                    "STORAGE_INFO_MEMO = #{storageInfoMemo}, " +
                    "LAST_UPDATED_TIMESTAMP = CURRENT_TIMESTAMP " +
                    "WHERE " +
                    "GROUP_ID = #{groupId} AND " +
                    "STORAGE_INFO_ID = #{storageInfoId} AND " +
                    "SOFT_DELETE_DIV = '0' "
    )int update(String groupId, int storageInfoId, String storageInfoName, String storageInfoPass, String storageInfoMemo);

    @Update(
            "UPDATE TR_STORAGE_INFO_MANAGE " +
                    "SET " +
                    "SOFT_DELETE_DIV = '1', " +
                    "LAST_UPDATED_TIMESTAMP = CURRENT_TIMESTAMP " +
                    "WHERE " +
                    "GROUP_ID = #{groupId} AND " +
                    "STORAGE_INFO_ID = #{storageInfoId}"
    ) int softDelete(String groupId, int storageInfoId);

    @Delete(
            "DELETE TR_STORAGE_INFO_MANAGE " +
                    "WHERE " +
                    "GROUP_ID = #{groupId} AND " +
                    "STORAGE_INFO_ID = #{storageInfoId}"
    ) int hardDelete(String groupId, int storageInfoId);
    @Select(
            "SELECT nextval('storage_info_id_seq');"
    )
    int generateSeq();
}
