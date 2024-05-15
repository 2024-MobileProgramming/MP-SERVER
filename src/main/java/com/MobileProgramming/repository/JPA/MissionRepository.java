package com.MobileProgramming.repository.JPA;

import com.MobileProgramming.dto.response.GetMissionDataResponse;

public interface MissionRepository {

    //유저의 미션 가져오기
    GetMissionDataResponse getMissionData(int userId, int missionId);

    //유저 미션 평가
    boolean postMissionVerificate(int verificateUserId, int verificatedUserId, int missionId);
}
