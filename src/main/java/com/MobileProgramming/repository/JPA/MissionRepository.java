package com.MobileProgramming.repository.JPA;

import com.MobileProgramming.dto.response.GetMissionDataResponse;

public interface MissionRepository {

    //유저의 미션 가져오기
    GetMissionDataResponse getMissionData(int userId, int missionId);
}
