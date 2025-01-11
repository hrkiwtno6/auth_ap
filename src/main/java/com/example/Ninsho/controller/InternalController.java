package com.example.Ninsho.controller;

import com.example.Ninsho.*;
import com.example.Ninsho.controller.dto.*;
import com.example.Ninsho.entity.StorageInfo;
import com.example.Ninsho.entity.User;
import com.example.Ninsho.service.RegistStorageInfoService;
import com.example.Ninsho.service.RegistUserService;
import com.example.Ninsho.service.SearchInfoService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

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
    RegistStorageInfoService registStorageInfoService;
    @Autowired
    SearchInfoService searchInfoService;

    class ResponseObject {
        @JsonProperty("http_sts")
        int http_sts = 0;
        @JsonProperty("res_sts")
        String res_sts = null;
        @JsonProperty("error_msg")
        String error_msg = "";

        public void setHttp_sts(int http_sts) {
            this.http_sts = http_sts;
        }

        public void setRes_sts(String res_sts) {
            this.res_sts = res_sts;
        }

        public void setError_msg(String error_msg) {
            this.error_msg = error_msg;
        }
    }



    @Component
    public class RequestBodyHandler {
        private final ObjectMapper objectMapper;

        public RequestBodyHandler(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        public <T> T convertTo(RequestBody requestBody, Class<T> targetClass) {
            try {
                return objectMapper.readValue(requestBody.toString(), targetClass);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to convert request body to " + targetClass.getSimpleName(), e);
            }
        }
    }

    ResponseObject responseObject = new ResponseObject();

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @PostMapping("/registUser")
    public ResponseEntity<String> registUser(RequestEntity<String> requestEntity) {
        final JsonNode requestJson;
        try{
            requestJson = objectMapper.readTree(requestEntity.getBody());
        }catch (JsonProcessingException e){
            return ResponseEntity.badRequest().body("request body is invalid");
        }
        UserInDto inDto = new UserInDto(requestJson);
        int userId = registUserService.exec(inDto.getLoginId(), inDto.getLoginPw());
        UserOutDto outDto = new UserOutDto(userId);
        return ResponseEntity.ok().body(outDto.getUserId());
    }

    @PostMapping("/api/login")
    public ResponseEntity<UserOutDto> login(RequestEntity<String> requestEntity) {
        final JsonNode requestJson;
        try{
            requestJson = objectMapper.readTree(requestEntity.getBody());
        }catch (JsonProcessingException e){
            return ResponseEntity.badRequest().body(null);
            //TODO レスポンスにRegistInfoOutDtoに対してエラーメッセージをつけて返すのか？エラーのthrow方式のお作法がわからない。
        }
        UserInDto inDto = new UserInDto(requestJson);
        int userId = ninshoService.exec(inDto.getLoginId(), inDto.getLoginPw());
        UserOutDto outDto = new UserOutDto(userId);
        return ResponseEntity.ok().body(outDto);
    }

    @PostMapping("/api/registInfo")
    public ResponseEntity<RegistInfoOutDto> registInfoController(RequestEntity<String> requestEntity) {
        //OutDtoのオブジェクトを＜＞に詰めておく。
        //自分で基底オブジェクトを作ってその中にOutDtoを詰めてみるのもあり。（デフォルト値を詰める必要がない。）
        //基底オブジェクトがResponseEntityを使って書く三弾構成にする。
        final JsonNode requestJson;
        try{
            requestJson = objectMapper.readTree(requestEntity.getBody());
        }catch (JsonProcessingException e){
            return ResponseEntity.badRequest().body(null);
            //TODO レスポンスにRegistInfoOutDtoに対してエラーメッセージをつけて返すのか？エラーのthrow方式のお作法がわからない。
        }
        RegistInfoInDto inDto = new RegistInfoInDto(requestJson);
        int storageInfoId = registStorageInfoService.exec(inDto.getGroupId(),inDto.getStorageInfoName(), inDto.getStorageInfoPass(), inDto.getStorageInfoMemo());
        RegistInfoOutDto outDto = new RegistInfoOutDto(storageInfoId);
        return ResponseEntity.ok().body(outDto);
    }

    @PostMapping("/api/search")

    public ResponseEntity<String> searchInfoController(RequestEntity<String> requestEntity) {
    final JsonNode requestJson;
    try{
        requestJson = objectMapper.readTree(requestEntity.getBody());
    }catch (JsonProcessingException e){
        return ResponseEntity.badRequest().body("request body is invalid");
    }

    final SearchInfoInDto inDto = new SearchInfoInDto(requestJson);
        final ArrayList<StorageInfo> storageInfoList = searchInfoService.exec(inDto.getGroupId());
        SearchInfoOutDto outDto = new SearchInfoOutDto(storageInfoList);
        return ResponseEntity.ok().body(outDto.getJson().toString());
    }
}