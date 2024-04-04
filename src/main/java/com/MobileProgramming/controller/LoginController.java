package com.MobileProgramming.controller;


import com.MobileProgramming.auth.KakaoService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Slf4j
@Controller
@AllArgsConstructor
public class LoginController {

    private KakaoService kakaoService;

    @RequestMapping(value = "/kakao/login")
    public String kakaoLogin(@RequestParam("code") String code, HttpSession session) throws Exception {
        log.info("로그인실행");
        String access_token = kakaoService.getToken(code);
        log.info("access_token : " + access_token);
        return "LOGIN_SUCCESS.html";
    }


    @GetMapping("/")
    public String Home() {

        log.info("index.html 접속");
        return "index.html";
    }


    @GetMapping("/login")
    public String kakaoConnect() {

        StringBuffer url = new StringBuffer();
        url.append("https://kauth.kakao.com/oauth/authorize?");
        url.append("client_id=" + "1cf1a6b0c5ddc8e2843d7cb635f6be69");
        url.append("&redirect_uri=http://localhost:8080/kakao/login");
        url.append("&response_type=code");
        log.info("redirect주소:" + url);
        return "redirect:" + url.toString();
    }

    @RequestMapping(value = "/kakao")
    public String kakaoLogin(@RequestParam("code") String code, Model model, HttpSession session) throws Exception {
//https://record1996.tistory.com/25
        String access_token = kakaoService.getToken(code);

        ArrayList<Object> list = kakaoService.getUserInfo(access_token);


        model.addAttribute("list", list);

        return "userInfo.html";
    }
}
