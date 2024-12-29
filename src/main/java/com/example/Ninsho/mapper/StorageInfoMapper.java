package com.example.Ninsho.mapper;

import com.example.Ninsho.entity.PasswordInfo;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface StorageInfoMapper {
    @ConstructorArgs({
            @Arg(column = "GROUP_ID", javaType = String.class, id = true,name = "groupId"),
            @Arg(column = "STORAGE_INFO_ID", javaType = String.class, id = true,name = "storageInfoId"),
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
                    "WHERE STORAGE_INFO_ID = #{storageInfoId}"
    )
    ArrayList<PasswordInfo> selectByPrimaryKey(String storageInfoId);

    @ConstructorArgs({
            @Arg(column = "GROUP_ID", javaType = String.class, id = true,name = "groupId"),
            @Arg(column = "STORAGE_INFO_ID", javaType = String.class, id = true,name = "storageInfoId"),
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
                    "WHERE GROUP_ID = #{groupId}"
    )
    ArrayList<PasswordInfo> selectALL(String groupId);
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

    @Select(
            "SELECT nextval('storage_info_id_seq');"
    )
    int generateSeq();
}
