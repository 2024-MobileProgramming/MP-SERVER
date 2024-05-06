package com.MobileProgramming.serviceImpl;

import com.MobileProgramming.domain.Team;
import com.MobileProgramming.domain.User;
import com.MobileProgramming.repository.JPA.JPAUserRepository;
import com.MobileProgramming.service.BaseService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Transactional //어떤 버전을 사용할것인가? springframework? java표준? --> https://interconnection.tistory.com/123
@Service
@Slf4j
public class BaseServiceImpl implements BaseService {
    private final JPAUserRepository jpaUserRepository;

    public BaseServiceImpl(JPAUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    // 매일 팀 만들기 구현
    @Override
    public void teamformation() {
        List<User> allUsers = jpaUserRepository.findAll(); // 모든 사용자 가져오기
        Collections.shuffle(allUsers); // 사용자 리스트 무작위로 섞기
        int teamId = 0;
        int perTeamSize = 5; // 한 팀당 인원
        int teamSize = 0; // 팀 구성 시 쓰는 변수
        List<Integer> teamMembers = new ArrayList<>(); // 각 팀당의 userId를 저장할 변수
        int lastTeamSize = allUsers.size() % perTeamSize; // 마지막 팀의 인원 수
        int totalTeamSize = allUsers.size() / perTeamSize + (lastTeamSize > 0 ? 1 : 0); // 총 팀 수

        Iterator<User> iterator = allUsers.iterator();

        while (iterator.hasNext()) {
            teamMembers.clear();
            while (teamSize < perTeamSize && iterator.hasNext()) {
                Integer temp = iterator.next().getUserId();
                teamMembers.add(temp);
                teamSize++;
            }
            saveTeamMembers(teamMembers, teamId);
            teamId++;
            teamSize = 0;
        }
    }

    private void saveTeamMembers(List<Integer> teamMembers, int teamId) {
        Date currentDate = new Date(System.currentTimeMillis()); // 현재 날짜
        for (Integer userId : teamMembers) {
            jpaUserRepository.saveTeam(new Team(userId, teamId, currentDate)); // 각 사용자별로 팀 저장
        }
    }

}
