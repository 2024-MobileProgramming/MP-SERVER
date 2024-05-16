package com.MobileProgramming.repository.JPA;


import com.MobileProgramming.domain.*;
import org.springframework.data.relational.core.sql.In;

import java.sql.Date;
import java.util.List;

public interface JPAUserRepository {

//    유저 저장
    void saveUser(User user);

//    이름으로 유저 찾기
    List<User> findUserByName(String name);

//    미션 가져오기. 한번에 20개의 미션을 받아서 여기서 5개를 추출하는 식으로 진행
    List<Mission> getAllMission();

//    모든 유저 찾아오기
    List<User> findAllUser();

//    팀의 개수 가져오기
    List<Integer> getTeamSize();

//    팀 저장하기
    void saveTeam(Team team);
//    팀 날리기

//    팀 가져오기
    List<Team> getAllTeamInfo();

//    Team에서 한명의 userId로 나머지 팀원의 정보 가져오기
    List<Integer> getUserIdsFromTeamByUserId(int UserId);

//    UserId로 몇번 팀인지 알아오기
    List<Integer> getTeamIdByUserId(int UserId);

//    UserId로 해당 유저가 무슨 미션을 수행중인지
    List<Integer> getMissionIdsByuserIdAndDate(int userId, Date date);


//    미션 아이디로 미션 설명 가져오기
    String getMissionDescriptionByMissionId(int MissionId);

//    미션 아이디로 미션 짧은 설명 가져오기
    String getShortDescriptionByMissionId(int MissionId);

    //미션 아이디로 미션 url 받아오기
    String getMissionUrlByMissionId(int MissionId);

    //미션 아이디로 승인 횟수 받아오기
    int getMissionVerificationCountByMissionIdAndUserId(int missionId, int userId);

    //미션 아이디, 유저아이디, 날짜로 이미지 받아오기
    byte[] getImageByMissionId(int userId, int MissionId);


//    미션 아이디로 미션 관련 내용 전부 가져오기
    List<Mission> getAllMissionInformationByMissionId(int MissionId);



//    각 userId마다 미션 할당하기(5개씩이 들어갈 것)
//    같은 팀에 같은 미션 할당하기는 CRUD_TEST 코드 참고.
    void allocation(UserMission userMission);


//    다른 User가 내가 미션 한 것을 인정해주기
//    UserId1은 인증을 받는 유저, UserId2는 인증을 하는 유저
//    MissionId는 어떤 미션인지 --> MissionId는 그냥 UserId1로부터 찾아와야함
//    해당 함수 실행시, userId2가 UserId1가 미션을 수행했다는 것에 동의하는 것으로 인정하고, UserMission의 verfication_count를 1 증가함.
    void verification(int UserId1, int UserId2, int MissionId);

//    UserId와 MissionId를 받아서 해당 미션에 대한 다른 유저의 인정 정보 가져오기
     List<Verification> getVerifierIDByIdAndMissionId(int userId, int missionId);

//     teamId로 그 팀에 속해있느 모든 유저의 ID 가져오기
     List<Integer> getUserIdsByTeamId(int teamId);
}
