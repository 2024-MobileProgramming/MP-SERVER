package com.MobileProgramming.serviceImpl;

import com.MobileProgramming.domain.Mission;
import com.MobileProgramming.domain.Team;
import com.MobileProgramming.domain.User;
import com.MobileProgramming.domain.UserMission;
import com.MobileProgramming.repository.JPA.JPAUserRepository;
import com.MobileProgramming.service.BaseService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Transactional
@Service
@Slf4j
public class BaseServiceImpl implements BaseService {
    int teamId;
    private final JPAUserRepository jpaUserRepository;

    public BaseServiceImpl(JPAUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    // 매일 팀 만들기 구현
    @Override
    public void teamformation() {
        List<User> allUsers = jpaUserRepository.findAllUser(); // 모든 사용자 가져오기
        Collections.shuffle(allUsers); // 사용자 리스트 무작위로 섞기
        teamId = 0;
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


    @Override
    public List<Integer> random5Mission() {
        int count = 5; // 한 유저에게 부여할 미션의 수

        List<Mission> allMissions = jpaUserRepository.getAllMission();
        return selectRand5Mission(allMissions, count);
    }

    public List<Integer> selectRand5Mission(List<Mission> allMissions, int count) {
        List<Integer> rand5MissionIds = new ArrayList<>();

        // 모든 미션 수가 count보다 작으면 모든 미션을 선택
        if (allMissions.size() <= count) {
            for (Mission mission : allMissions) {
                rand5MissionIds.add(mission.getMissionId());
            }
        } else {
            Random random = new Random();
            Set<Integer> selectedIndices = new HashSet<>();

            // count개의 미션을 선택할 때까지 반복
            while (rand5MissionIds.size() < count) {
                // 무작위 인덱스 선택
                int randomIndex = random.nextInt(allMissions.size());
                // 이미 선택된 인덱스인지 확인
                if (!selectedIndices.contains(randomIndex)) {
                    // 선택된 인덱스를 추가하고 미션 ID를 결과 리스트에 추가
                    selectedIndices.add(randomIndex);
                    rand5MissionIds.add(allMissions.get(randomIndex).getMissionId());
                }
            }
        }

        return rand5MissionIds;
    }


    @Scheduled(cron = "0 0 0 * * *")
    @Override
    public void scheduling() {
//        먼저 팀 형성하고
        teamformation();
        System.out.println("팀 형성 단계 완료");
//        모든 팀을 가져옴
        System.out.println("팀 가져오기 완료, 총 팀 개수:" + teamId);
        for (int j = 0; j < teamId; j++) {
            // 팀아이디에 속해있는 모든 유저 아이디 가져오기
            List<Integer> userIdsByTeamId = jpaUserRepository.getUserIdsByTeamId(j);

            for (Integer userId : userIdsByTeamId) {
                List<Integer> userMissions = random5Mission();
                for (Integer missionId : userMissions) {
                    System.out.println(userId + "유저에게 " + missionId + "번 미션 할당해주기");
                    Date date = new Date(System.currentTimeMillis());
                    jpaUserRepository.allocation(new UserMission(userId, missionId, 0, date));
                }
            }
        }
    }
}
