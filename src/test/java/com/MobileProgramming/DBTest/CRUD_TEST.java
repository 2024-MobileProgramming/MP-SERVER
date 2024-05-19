package com.MobileProgramming.DBTest;


import com.MobileProgramming.domain.*;
import com.MobileProgramming.repository.JPA.JPAUserRepositoryImpl;
import com.MobileProgramming.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@Transactional
@Slf4j
public class CRUD_TEST {

    @Autowired
    JPAUserRepositoryImpl jpaUserRepository;
    @Autowired
    BaseService baseService;
    int testuserid = 1;
    int verfierid = 2;
    int verfierid2 = 3;

    int testMissionId = 1;

    Date currentDate = new Date(System.currentTimeMillis());//오늘 날짜로 테스트 돌리기

    //    어제날짜도 사용가능
    LocalDate today = LocalDate.now();
    LocalDate yesterday = today.minusDays(1);
    Date dateYesterday = Date.valueOf(yesterday);//어제 날짜 사용도 가능

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
        missionlist = jpaUserRepository.getAllMission();
        for (Mission mission : missionlist) {
            System.out.println(mission);
        }
    }

    @Test
    @DisplayName("모든 유저 찾아오기")
    void findAllUsers() {
        List<User> list = new ArrayList<>();
        list = jpaUserRepository.findAllUser();
        for (User userId : list) {
            System.out.println(userId);
        }
    }

    @Test
    @DisplayName("팀 저장하기")
    void saveNewTeam() {
        long currentTimeMillis = System.currentTimeMillis();
        java.sql.Date currentDate = new java.sql.Date(currentTimeMillis);

        Team newTeam = new Team(testuserid, 3, currentDate);
        jpaUserRepository.saveTeam(newTeam);

        Team savedTeam = jpaUserRepository.getAllTeamInfo().get(0);

        Assertions.assertEquals(newTeam.getUserId(), savedTeam.getUserId());
        Assertions.assertEquals(newTeam.getTeamId(), savedTeam.getTeamId());
        Assertions.assertEquals(String.valueOf(newTeam.getUpdatedDate()), String.valueOf(savedTeam.getUpdatedDate()));
    }


    //테이블 수정!!!팀 만든 날짜 넣어야함
    @Test
    @DisplayName("팀에서 teamId로 UserId'들'가져오기")
    void getuserIDsFromTeamByUserId() {
        baseService.teamformation();
        List<Team> teamList = jpaUserRepository.getAllTeamInfo();
        System.out.println("팀 출력");
        for (Team team : teamList) {
            System.out.println(team.toString());
        }
        System.out.println("팀 출력 완료");
        List<Integer> userIdsFromTeamByUserId = jpaUserRepository.getUserIdsFromTeamByUserId(testuserid);
        for (Integer i : userIdsFromTeamByUserId) {
            System.out.println(testuserid + "번이랑 같은 팀인 ID들" + i);
        }
    }

    @Test
    @DisplayName("User가 무슨 미션 수행 중인지 알아오기, 미션 할당 하려면 이 코드 참고")
    void getMissionsByUserId() {
        baseService.teamformation();
        List<Integer> missionIds = baseService.random5Mission();
        List<Integer> teamMemberIds = jpaUserRepository.getUserIdsFromTeamByUserId(testuserid);

        Collections.sort(missionIds);

        System.out.println("무작위 미션 아이디 5개 출력하기");
        for (Integer missionId : missionIds) {
            System.out.println(missionId);
        }

        System.out.println(testuserid + "와 팀인 유저들");
        for (Integer teamMemberId : teamMemberIds) {
            System.out.println(teamMemberId);
        }

        System.out.println("현재날짜" + currentDate);
        for (Integer teamMemberId : teamMemberIds) {
            for (Integer missionId : missionIds) {
                UserMission userMission = new UserMission(teamMemberId, missionId, 0, currentDate);
                jpaUserRepository.allocation(userMission);
            }
        }
        System.out.println("DB 저장 후에 테스트 유저가 수행중인 미션아이디들 출력");
        List<Integer> missionIdByuserId = jpaUserRepository.getMissionIdsByuserIdAndDate(testuserid, currentDate);
        for (Integer i : missionIdByuserId) {
            System.out.println(i);
        }
    }

    @Test
    @DisplayName("인증이 잘 되는지 확인하기")
    void test() {
        getMissionsByUserId(); //미션할당완료

        List<Integer> missionIdByuserId = jpaUserRepository.getMissionIdsByuserIdAndDate(testuserid, currentDate);
        System.out.println("테스트유저의 미션아이디들");
        for (Integer i : missionIdByuserId) {
            List<Mission> allMissionInformationByMissionId = jpaUserRepository.getAllMissionInformationByMissionId(i);
            for (Mission mission : allMissionInformationByMissionId) {
                System.out.println(mission);
            }
        }
        System.out.println("첫번째(가장 작은 숫자의) 미션을 2번ID인 verifier가 인증했다고 가정할 때 verifier가 잘 돌아오는가?(날짜는 오늘 날짜로)");
        jpaUserRepository.verification(testuserid, verfierid, jpaUserRepository.getMissionIdsByuserIdAndDate(testuserid, currentDate).get(0));
        System.out.println("첫번째(가장 작은 숫자의) 미션을 3번ID인 verifier가 인증했다고 가정할 때 verifier가 잘 돌아오는가?2(날짜는 오늘 날짜로)");
        jpaUserRepository.verification(testuserid, verfierid2, jpaUserRepository.getMissionIdsByuserIdAndDate(testuserid, currentDate).get(0));
        System.out.println("미션ID가 " + jpaUserRepository.getMissionIdsByuserIdAndDate(testuserid, currentDate).get(0) + "인 미션에 대해 동의한 유저");
        System.out.println("testuserId의 미션정보들:");
        List<Verification> verifiedMissionsByIdAndMissionId =
                jpaUserRepository.getVerifierIDByIdAndMissionId(testuserid, jpaUserRepository.getMissionIdsByuserIdAndDate(testuserid, currentDate).get(0));
        for (Verification verification : verifiedMissionsByIdAndMissionId) {
            System.out.println(verification.getVerifierId());
        }
    }

    @Test
    @DisplayName("팀아이디로 해당 팀의 userid들 가져오기")
    void getUserIdsByTeamId() {
        baseService.teamformation();
        List<Integer> userIdsByTeamId = jpaUserRepository.getUserIdsByTeamId(0);
        System.out.println(userIdsByTeamId.toString());
    }

    @Test
    @DisplayName("미션 전체 정보 가져오기(ID로 하나의 미션만)")
    void getMissionInformation() {
        List<Mission> allMissionInformationByMissionId = jpaUserRepository.getAllMissionInformationByMissionId(testMissionId);
        System.out.println(allMissionInformationByMissionId);
    }

    @Test
    @DisplayName("짧은 설명 가져오기(ID로 하나의 미션만)")
    void getShortDescription() {
        String shortDescription = jpaUserRepository.getShortDescriptionByMissionId(testMissionId);
            System.out.println(shortDescription);
    }

    @Test
    @DisplayName("유저아이디로 이름 가져오기")
    void getusrNamebyuserId() {
        List<String> userNameByUserId = jpaUserRepository.getUserNameByUserId(testuserid);
        for (String s : userNameByUserId) {
            System.out.println(s);
        }
    }

    @Test
    @DisplayName("미션아이디로 미션 제목 가져오기")
    void getMissionTitleByMissionId() {
        List<String> missionTitleByMissionId = jpaUserRepository.getMissionTitleByMissionId(testMissionId);
        for (String s : missionTitleByMissionId) {
            System.out.println(s);

        }
    }
}
