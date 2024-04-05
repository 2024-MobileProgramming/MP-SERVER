package com.MobileProgramming.auth;

import java.util.ArrayList;


public interface KakaoService {

    public String getToken(String code) throws Exception;

    public ArrayList<Object> getUserInfo(String access_token) throws Exception;
}
