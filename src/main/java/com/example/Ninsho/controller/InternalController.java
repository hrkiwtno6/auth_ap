package com.example.Ninsho.controller;

import com.example.Ninsho.*;
import com.example.Ninsho.controller.dto.*;
import com.example.Ninsho.entity.PasswordInfo;
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
    public void registUser(@RequestBody UserInDto userInDto) {
        registUserService.registUser(userInDto.getGroupId(), userInDto.getUserId(), userInDto.getPass());
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String loginStatus = ninshoService.certificete(request.get("id"), request.get("pass"));
        if (loginStatus.equals(NinshoConstants.LOGIN_SUCCESSFUL)) {
            return ResponseEntity.ok().build();
        } else {
            //TODO error_msgに値を詰めて返却するようにしたい。下にそのコードを書く。
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/api/registInfo")
    public ResponseEntity<String> registInfoController(RequestEntity<String> requestEntity) {
        //OutDtoのオブジェクトを＜＞に詰めておく。
        //自分で基底オブジェクトを作ってその中にOutDtoを詰めてみるのもあり。（デフォルト値を詰める必要がない。）
        //基底オブジェクトがResponseEntityを使って書く三弾構成にする。
        final JsonNode requestJson;
        try{
            requestJson = objectMapper.readTree(requestEntity.getBody());
        }catch (JsonProcessingException e){
            return ResponseEntity.badRequest().body("request body is invalid");
        }
        RegistInfoInDto inDto = new RegistInfoInDto(requestJson);
        int storageInfoId = registStorageInfoService.registStrageInfo(inDto.getGroupId(),inDto.getStorageInfoName(), inDto.getStorageInfoPass(), inDto.getStorageInfoMemo());
        RegistInfoOutDto outDto = new RegistInfoOutDto(storageInfoId);
        return ResponseEntity.ok().body(outDto.getStorageInfoId());
    }

    @PostMapping("/api/cert")
    public String certUserController(@RequestBody UserInDto userInDto) {
        String loginStatus = ninshoService.certificete(userInDto.getUserId(), userInDto.getPass());

        //TODO 認証履歴にレコードぶっこむ処理を作っときたい。
        //TODO error_msgに値を詰めて返却するようにしたい。
        responseObject.setHttp_sts(HttpStatus.OK.value());
        responseObject.setRes_sts(loginStatus);
        String jsonString = null;
        try {
            // UserオブジェクトをJSON文字列に変換
            jsonString = objectMapper.writeValueAsString(responseObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }
    @PostMapping("/api/search")

    public ResponseEntity<String> searchInfoController(RequestEntity<String> requestEntity) {
    final JsonNode requestJson;
    try{
        requestJson = objectMapper.readTree(requestEntity.getBody());
    }catch (JsonProcessingException e){
        return ResponseEntity.badRequest().body("request body is invalid");
    }

    final SearchInfoInDto inDto = new SearchInfoInDto();
        final ArrayList<PasswordInfo> passwordInfoList = searchInfoService.exec(inDto.getGroupId());
        SearchInfoOutDto outDto = new SearchInfoOutDto(passwordInfoList);
        return ResponseEntity.ok().body(outDto.getJson().toString());
    }
}