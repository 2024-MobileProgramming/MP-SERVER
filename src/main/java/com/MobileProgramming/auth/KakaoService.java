package com.MobileProgramming.auth;

import org.springframework.stereotype.Service;

import java.util.ArrayList;


public interface KakaoService {

    public String getToken(String code) throws Exception ;
    public ArrayList<Object> getUserInfo(String access_token) throws Exception  ;
}
