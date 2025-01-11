package com.example.Ninsho.mapper;

import com.example.Ninsho.entity.StorageInfo;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface StorageInfoMapper {
    @ConstructorArgs({
            @Arg(column = "GROUP_ID", javaType = String.class,name = "groupId"),
            @Arg(column = "STORAGE_INFO_ID", javaType = int.class, id = true,name = "storageInfoId"),
            @Arg(column = "STORAGE_INFO_NAME", javaType = String.class,name = "storageInfoName"),
            @Arg(column = "STORAGE_INFO_PASS", javaType = String.class,name = "storageInfoPass"),
            @Arg(column = "STORAGE_INFO_MEMO", javaType = String.class,name = "storageInfoMemo")
    })
    @Select(
            "SELECT " +
                    "GROUP_ID, " +
                    "STORAGE_INFO_ID, " +
                    "STORAGE_INFO_NAME, " +
                    "STORAGE_INFO_PASS, " +
                    "STORAGE_INFO_MEMO " +
                    "FROM TR_STORAGE_INFO_MANAGE " +
                    "WHERE STORAGE_INFO_ID = #{storageInfoId}" +
                    "ORDER BY STORAGE_INFO_ID"
    )
    ArrayList<StorageInfo> selectByPrimaryKey(int storageInfoId);

    @ConstructorArgs({
            @Arg(column = "GROUP_ID", javaType = String.class, name = "groupId"),
            @Arg(column = "STORAGE_INFO_ID", javaType = int.class, id = true,name = "storageInfoId"),
            @Arg(column = "STORAGE_INFO_NAME", javaType = String.class, name = "storageInfoName"),
            @Arg(column = "STORAGE_INFO_PASS", javaType = String.class, name = "storageInfoPass"),
            @Arg(column = "STORAGE_INFO_MEMO", javaType = String.class, name = "storageInfoMemo")
    })
    @Select(
            "SELECT " +
                    "GROUP_ID, " +
                    "STORAGE_INFO_ID, " +
                    "STORAGE_INFO_NAME, " +
                    "STORAGE_INFO_PASS, " +
                    "STORAGE_INFO_MEMO " +
                    "FROM TR_STORAGE_INFO_MANAGE " +
                    "WHERE GROUP_ID = #{groupId}" +
                    "ORDER BY GROUP_ID,STORAGE_INFO_NAME"
    )
    ArrayList<StorageInfo> selectALL(String groupId);
    @Insert(
            "INSERT INTO TR_STORAGE_INFO_MANAGE " +
                    "(GROUP_ID, STORAGE_INFO_ID, STORAGE_INFO_NAME, STORAGE_INFO_PASS, STORAGE_INFO_MEMO) " +
                    "VALUES " +
                    "(#{groupId}," +
                    " #{storageInfoId}," +
                    " #{storageInfoName}," +
                    " #{storageInfoPass}," +
                    " #{storageInfoMemo}" +
                    ")"
    )
    void regist(String groupId, int storageInfoId, String storageInfoName, String storageInfoPass, String storageInfoMemo);

    @Update(
            "UPDATE TR_STORAGE_INFO_MANAGE " +
                    "SET " +
                    "STORAGE_INFO_NAME = #{storageInfoName}, " +
                    "STORAGE_INFO_PASS = #{storageInfoPass}, " +
                    "STORAGE_INFO_MEMO = #{storageInfoMemo}, " +
                    "LAST_UPDATED_TIMESTAMP = CURRENT_TIMESTAMP " +
                    "WHERE " +
                    "GROUP_ID = #{groupId} AND " +
                    "STORAGE_INFO_ID = #{storageInfoId}"
    )int update(String groupId, int storageInfoId, String storageInfoName, String storageInfoPass, String storageInfoMemo);

    @Select(
            "SELECT nextval('storage_info_id_seq');"
    )
    int generateSeq();
}
