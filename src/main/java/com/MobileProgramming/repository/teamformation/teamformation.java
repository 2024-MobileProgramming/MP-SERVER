package com.MobileProgramming.repository.teamformation;

import com.MobileProgramming.domain.Team;
import com.MobileProgramming.domain.User;
import com.MobileProgramming.repository.JPA.JPAUserRepository;

import java.util.*;

import java.sql.Date;

// 팀을 만들기 위한 기능.
// 매일 10명이 한 팀이 되어 미션을 수행하기 위해 팀빌딩이 이루어져야함
// 무작위로 10명을 팀으로 만들고 DB에 저장할 것임.
public class teamformation {
    private final JPAUserRepository jpaUserRepository;

    public teamformation(JPAUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    public void makeTeam() {
        jpaUserRepository.teamDailyRenewal();
        List<User> allUsers = jpaUserRepository.findAll(); // 모든 사용자 가져오기
        Collections.shuffle(allUsers); // 사용자 리스트 무작위로 섞기

        int teamSize = 10; // 한 팀당 인원
        int lastTeamSize = allUsers.size() % teamSize; // 마지막 팀의 인원 수
        int teamNum = allUsers.size() / teamSize + (lastTeamSize > 0 ? 1 : 0); // 총 팀 수

        Iterator<User> iterator = allUsers.iterator();

        for (int i = 1; i <= teamNum; i++) {
            List<User> teamMembers = new ArrayList<>();
            for (int j = 0; j < teamSize && iterator.hasNext(); j++) {
                teamMembers.add(iterator.next()); // 사용자를 팀에 추가
            }

            // 마지막 팀의 경우 남은 인원만큼 추가
            if (i == teamNum && lastTeamSize > 0) {
                for (int j = 0; j < lastTeamSize; j++) {
                    teamMembers.add(iterator.next());
                }
            }

            // 팀 정보를 데이터베이스에 저장
//            saveTeam(teamMembers, i);
        }

    }

    private void saveTeam(List<User> teamMembers, int teamId) {
        Date currentDate = new Date(System.currentTimeMillis()); // 현재 날짜
        for (User user : teamMembers) {
            // 각 팀 멤버마다 팀 정보를 저장
            Team team = new Team(user.getUserId(), teamId, currentDate);
//            jpaUserRepository.saveTeam(team);
        }
    }
}
