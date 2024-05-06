package com.MobileProgramming.DBTest;


import com.MobileProgramming.domain.Mission;
import com.MobileProgramming.domain.Team;
import com.MobileProgramming.domain.User;
import com.MobileProgramming.repository.JPA.JPAUserRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
//@Transactional
@Slf4j
public class CRUD_TEST {

    @Autowired
    JPAUserRepositoryImpl jpaUserRepository;

    @Test
    @DisplayName("User Save 가능 확인 테스트")
//    만약 여기서 Execution failed for task ':test'. 오류 뜬다면
//    https://dev-emmababy.tistory.com/86
//    원래 함수명이 한글이면 떴던 오류인데 그냥 영어로 바꿈. 무시해도됨
    void userSave() {
        User user1 = new User("권하림", "nickname", "blizzard777@gachon.ac.kr", "010-3046-7743");
        jpaUserRepository.saveUser(user1);
    }

    @Test
    @DisplayName("저장된 유저가 Spring으로 잘 연동되었는지 확인하는 테스트. 얘는 직접 비교해봄")
    void userconfirm() {
        User user1 = new User("권하림", "nickname", "blizzard777@gachon.ac.kr", "010-3046-7743");
        jpaUserRepository.saveUser(user1);
        List<User> foundUser = jpaUserRepository.findUserByName("권하림");
        for (User user : foundUser) {
            System.out.println(user.getName() + ", " + user.getNickname() + ", " + user.getEmail() + ", " + user.getPhoneNumber());
        }
    }

    @Test
    @DisplayName("미션 리스트 받아오기")
    void mission() {
        List<Mission> missionlist = new ArrayList<>();
        missionlist = jpaUserRepository.getMission();
        for (Mission mission : missionlist) {
            System.out.println(mission);
        }
    }

    @Test
    @DisplayName("모든 유저 찾아오기")
    void findAllUsers() {
        List<User> list = new ArrayList<>();
        list = jpaUserRepository.findAll();
        for (User userId : list) {
            System.out.println(userId);
        }
    }

    @Test
    @DisplayName("팀 저장하기")
    void saveNewTeam() {
        long currentTimeMillis = System.currentTimeMillis();
        java.sql.Date currentDate = new java.sql.Date(currentTimeMillis);

        Team newTeam = new Team(1, 3, currentDate);
        jpaUserRepository.saveTeam(newTeam);

        Team savedTeam = jpaUserRepository.getTeam().get(0);

        Assertions.assertEquals(newTeam.getUserId(), savedTeam.getUserId());
        Assertions.assertEquals(newTeam.getTeamId(), savedTeam.getTeamId());
        Assertions.assertEquals(String.valueOf(newTeam.getUpdatedDate()), String.valueOf(savedTeam.getUpdatedDate()));

    }
}
