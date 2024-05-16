package com.MobileProgramming.service;

import org.springframework.data.relational.core.sql.In;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface BaseService {
//    팀 구성 함수. BaseServiceImpl의 saveTeamMembers함수도 같이 사용.
    void teamformation();

//    미션들 중에서 랜덤한 5개의 미션아이디를 리턴해주는 함수, userId를 입력받아서 해당 유저에게 줌
//    그런데 주는 유저와 같은 팀인 유저들에게는 모두 같은 미션을 할당해줘야함
//    이건 전혀 다른 기능
    List<Integer> random5Mission();

//    스케줄링할 클래스. 하루에 한번 자정 12시에 실행되는 함수
    void scheduling();
}
