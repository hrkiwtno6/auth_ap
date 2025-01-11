package com.example.Ninsho.controller;

import com.example.Ninsho.controller.dto.*;
import com.example.Ninsho.entity.StorageInfo;
import com.example.Ninsho.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class InternalController {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    //AutowiredをつけておくとSpringが勝手にnewしてくれる。
    //アノテーションの後ろ1行に対して適応されるもの。
    //AutowiredとServiceはセットで使う。どっちが抜けててもダメ。
    NinshoService ninshoService;  //メソッドを動かすためのオブジェクトはnewしなくていい。データを入れるためのDtoなどはnewしないと使えない。
    @Autowired
    RegistUserService registUserService;
    @Autowired
    SearchInfoService searchInfoService;
    @Autowired
    RegistStorageInfoService registStorageInfoService;
    @Autowired
    UpdateStorageInfoService updateStorageInfoService;

    @GetMapping("/hello")
    public String v1Hello() {
        return "Hello, World!";
    }

    @PostMapping("/registUser")
    public ResponseEntity<LoginOutDto> v1RegistUser(RequestEntity<String> requestEntity) {
        final JsonNode requestJson;
        try {
            requestJson = objectMapper.readTree(requestEntity.getBody());
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(null);
            //TODO レスポンスにRegistInfoOutDtoに対してエラーメッセージをつけて返すのか？エラーのthrow方式のお作法がわからない。
        }
        LoginInDto inDto = new LoginInDto(requestJson);
        int userId = registUserService.exec(inDto.getLoginId(), inDto.getLoginPw());
        LoginOutDto outDto = new LoginOutDto(userId);
        return ResponseEntity.ok().body(outDto);
    }

    @PostMapping("/api/login")
    public ResponseEntity<LoginOutDto> v1Login(RequestEntity<String> requestEntity) {
        final JsonNode requestJson;
        try {
            requestJson = objectMapper.readTree(requestEntity.getBody());
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(null);
            //TODO レスポンスにRegistInfoOutDtoに対してエラーメッセージをつけて返すのか？エラーのthrow方式のお作法がわからない。
        }
        LoginInDto inDto = new LoginInDto(requestJson);
        int userId = ninshoService.exec(inDto.getLoginId(), inDto.getLoginPw());
        LoginOutDto outDto = new LoginOutDto(userId);
        return ResponseEntity.ok().body(outDto);
    }
    @PostMapping("/api/getStorageInfoList")
    public ResponseEntity<String> v1SearchInfo(RequestEntity<String> requestEntity) {
        final JsonNode requestJson;
        try {
            requestJson = objectMapper.readTree(requestEntity.getBody());
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("request body is invalid");
        }

        final GetStorageInfoListInDto inDto = new GetStorageInfoListInDto(requestJson);
        final ArrayList<StorageInfo> storageInfoList = searchInfoService.exec(inDto.getGroupId());
        GetStorageInfoListOutDto outDto = new GetStorageInfoListOutDto(storageInfoList);
        return ResponseEntity.ok().body(outDto.getJson().toString());
    }
    @PostMapping("/api/registStorageInfo")
    public ResponseEntity<RegistStorageInfoOutDto> v1RegistInfo(RequestEntity<String> requestEntity) {
        //OutDtoのオブジェクトを＜＞に詰めておく。
        //自分で基底オブジェクトを作ってその中にOutDtoを詰めてみるのもあり。（デフォルト値を詰める必要がない。）
        //基底オブジェクトがResponseEntityを使って書く三弾構成にする。
        final JsonNode requestJson;
        try {
            requestJson = objectMapper.readTree(requestEntity.getBody());
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(null);
            //TODO レスポンスにRegistInfoOutDtoに対してエラーメッセージをつけて返すのか？エラーのthrow方式のお作法がわからない。
        }
        RegistStorageInfoInDto inDto = new RegistStorageInfoInDto(requestJson);
        int storageInfoId = registStorageInfoService.exec(inDto.getGroupId(), inDto.getStorageInfoName(), inDto.getStorageInfoPass(), inDto.getStorageInfoMemo());
        RegistStorageInfoOutDto outDto = new RegistStorageInfoOutDto(storageInfoId);
        return ResponseEntity.ok().body(outDto);
    }
    @PostMapping("/api/updateStorageInfo")
    public ResponseEntity<RegistStorageInfoOutDto> v1UpdateStorageInfo(RequestEntity<String> requestEntity) {
        final JsonNode requestJson;
        try {
            requestJson = objectMapper.readTree(requestEntity.getBody());
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(null);
            //TODO レスポンスにRegistInfoOutDtoに対してエラーメッセージをつけて返すのか？エラーのthrow方式のお作法がわからない。
        }
        UpdateStorageInfoInDto inDto = new UpdateStorageInfoInDto(requestJson);
        int parseStorageInfoId = Integer.parseInt(inDto.getStorageInfoId());
        int storageInfoId = updateStorageInfoService.exec(inDto.getGroupId(), parseStorageInfoId, inDto.getStorageInfoName(), inDto.getStorageInfoPass(), inDto.getStorageInfoMemo());
        RegistStorageInfoOutDto outDto = new RegistStorageInfoOutDto(storageInfoId);
        return ResponseEntity.ok().body(outDto);
    }
//    @PostMapping("/api/deleteStorageInfo")
//    public ResponseEntity<RegistStorageInfoOutDto> v1DeleteStorageInfo(RequestEntity<String> requestEntity) {
//        final JsonNode requestJson;
//        try {
//            requestJson = objectMapper.readTree(requestEntity.getBody());
//        } catch (JsonProcessingException e) {
//            return ResponseEntity.badRequest().body(null);
//            //TODO レスポンスにRegistInfoOutDtoに対してエラーメッセージをつけて返すのか？エラーのthrow方式のお作法がわからない。
//        }
//        DeleteStorageInfoInDto inDto = new DeleteStorageInfoInDto(requestJson);
//        int parseStorageInfoId = Integer.parseInt(inDto.getStorageInfoId());
//        int storageInfoId = updateStorageInfoService.exec(inDto.getGroupId(), parseStorageInfoId);
//        RegistStorageInfoOutDto outDto = new RegistStorageInfoOutDto(storageInfoId);
//        return ResponseEntity.ok().body(outDto);
//    }
}
