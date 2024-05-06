package com.MobileProgramming.auth;

import com.MobileProgramming.domain.User;
import com.MobileProgramming.repository.BaseRepositoryImpl;
import com.MobileProgramming.repository.JPA.JPAUserRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Service
@Slf4j
public class KakaoServiceImpl implements KakaoService {
    BaseRepositoryImpl baseRepository;
    public KakaoServiceImpl(BaseRepositoryImpl baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public String getToken(String code) throws Exception {
        String access_Token = "";

        //EndPoint URL = API가 서버에서 자원에 접근할 수 있도록 하는 URL
        final String requestUrl = "https://kauth.kakao.com/oauth/token";

        //토큰을 요청할 URL 객체 생성
        URL url = new URL(requestUrl);

        //HTTP 연결 설정
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        //서버로 요청 보내기
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=authorization_code");
        sb.append("&client_id=1cf1a6b0c5ddc8e2843d7cb635f6be69");
        sb.append("&redirect_uri=http://localhost:8080/kakao/login");
        sb.append("&code=" + code);
        bw.write(sb.toString());

        bw.flush();

        //서버의 응답 데이터 가져옴
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line = "";
        String result = "";

        //result에 토큰이 포함된 응답데이터를 한줄씩 저장
        while ((line = br.readLine()) != null) {
            result += line;
        }

        //JSON 데이터를 파싱하기 위한 JsonParser
        JsonParser parser = new JsonParser();
        //값 추출을 위해 파싱한 데이터를 JsonElement로 변환
        JsonElement element = parser.parse(result);

        //element에서 access_token 값을 얻어옴
        access_Token = element.getAsJsonObject().get("access_token").getAsString();

        br.close();
        bw.close();

        return access_Token;

    }

    @Override
    public ArrayList<Object> getUserInfo(String access_token) throws Exception {

        ArrayList<Object> list = new ArrayList<Object>();

        final String requestUrl = "https://kapi.kakao.com/v2/user/me";

        URL url = new URL(requestUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + access_token);

        BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line = "";
        String result = "";

        while ((line = bf.readLine()) != null) {
            result += line;
        }

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(result);

        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
        JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

        //콘솔창 확인 후 필요한 정보 추출
        System.out.println("----------properties" + properties);
        System.out.println("----------kakao_account" + kakao_account);

///////////////////////////////////////////////////////////////////////


//        민영님 확인하시고 지워주세요.
//        아래 부분이 저희가 DB 저장하는 부분인데 닉네임은 앱에서 설정하고 나머지 부분만 저장할까 합니다.
//        그리고 카카오 로그인말고 앱 자체에서 회원가입도 가능해서
//        만약 카카오 회원가입으로 진입 시 밑에 주석으로 제외한 정보들 말고 필요한 정보들만 다시 앱으로 전송해서 유저가 변경못하고 설정할 수 있는 건 닉네임 부분만으로
//        설정할까 하고 만약 앱 자체 로그인은 다르게 구현해야할 것 같아요.
//        일단은 제가 혼자 진행하고 있는 부분이 많아지고 있어서 최대한 남겨놓으면서 진행하겠습니다.

///////////////////////////////////////////////////////////////////////


//        String thumbnail_image = properties.getAsJsonObject().get("profile_image").getAsString();
        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
        String email = kakao_account.getAsJsonObject().get("email").getAsString();
//        String gender = kakao_account.getAsJsonObject().get("gender").getAsString();
//        String birthday = kakao_account.getAsJsonObject().get("birthday").getAsString();
//        String birthyear = kakao_account.getAsJsonObject().get("birthyear").getAsString();
        String phone_number = kakao_account.getAsJsonObject().get("phone_number").getAsString();
        String name = kakao_account.getAsJsonObject().get("name").getAsString();


//        list.add(birthyear);
        list.add(phone_number);
//        list.add(thumbnail_image);
        list.add(nickname);
        list.add(email);
        list.add(name);
//        list.add(birthday);

        log.info("사용자 정보:" + String.valueOf(list));

//      DB에 저장하려고 했는데 여기서는 repository의 객체가 생성되지 않아서 사용할수가없습니다.
//      그렇다고 여기서 repository의 기능들을 구현하는 거도 말이 안되는거같아서
//      BaseRepositoryImpl로 보내서 하는 식으로 구현했습니다.
//      위에 말씀드렸듯이 나중에 카카오 로그인 아닌 것도 구현해야해서 카카오 로그인 아닐 시에도
//        이렇게 baseRepository 이용해서 하는 것이 좋다 판단해서 이렇게 만들어봤는데 더 간단하게 구현할 수 있는
//        아이디어가 있으시다면 알려주세요.
        User kakaouser = new User(name, nickname, email, phone_number);
        baseRepository.saveUser(kakaouser);

//
        return list;
    }
}