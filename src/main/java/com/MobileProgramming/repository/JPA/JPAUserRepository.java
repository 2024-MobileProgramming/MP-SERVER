package com.MobileProgramming.repository.JPA;


import com.MobileProgramming.domain.Mission;
import com.MobileProgramming.domain.Team;
import com.MobileProgramming.domain.User;

import java.util.List;

public interface JPAUserRepository {

//    유저 저장
    void saveUser(User user);

//    이름으로 유저 찾기
    List<User> findUserByName(String name);

//    미션 가져오기. 한번에 20개의 미션을 받아서 여기서 5개를 추출하는 식으로 진행
    List<Mission> getMission();

//    모든 유저 찾아오기
    List<User> findAll();


//    팀 저장하기
    void saveTeam(Team team);
//    팀 날리기

//    팀 가져오기
    List<Team> getTeam();

//    오늘의 친구 5명 찾아오기
//    예시로 10명 넣어놓음

//    오늘의 친구 설정하기

//    미션 인증 기능

}
