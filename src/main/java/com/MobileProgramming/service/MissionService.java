package com.MobileProgramming.service;

import com.MobileProgramming.dto.request.PostMissionVerficateRequest;
import com.MobileProgramming.dto.response.GetMissionDataResponse;
import com.MobileProgramming.repository.JPA.JPAUserRepositoryImpl;
import com.MobileProgramming.repository.JPA.MissionRepositoryImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import static com.MobileProgramming.exception.ErrorMessage.GOAL_NOT_FOUND_EXCEPTION;

@Service
@Transactional
public class MissionService {
    private final MissionRepositoryImpl jpaMissionRepositoryImpl;
    private final JPAUserRepositoryImpl jpaUserRepositoryImpl;

    public MissionService(MissionRepositoryImpl jpaMissionRepositoryImpl, JPAUserRepositoryImpl jpaUserRepositoryImpl) {
        this.jpaMissionRepositoryImpl = jpaMissionRepositoryImpl;
        this.jpaUserRepositoryImpl = jpaUserRepositoryImpl;
    }

    //특정유저의 1개의 미션에 대한 정보 get method
    @Transactional
    public GetMissionDataResponse getMissionData(int userId, int missionId) throws Exception {
        if (jpaMissionRepositoryImpl.getMissionData(userId, missionId) == null)
            throw new Exception(GOAL_NOT_FOUND_EXCEPTION.getMessage());
        // 카운트, 이미지 받아오기, 장문 설명, url 받아오기
        return new GetMissionDataResponse(missionId,
                jpaUserRepositoryImpl.getImageByMissionId(userId, missionId),
                jpaUserRepositoryImpl.getMissionVerificationCountByMissionIdAndUserId(missionId, userId),
                jpaUserRepositoryImpl.getMissionDescriptionByMissionId(missionId),
                jpaUserRepositoryImpl.getMissionUrlByMissionId(missionId));
    }

    @Transactional
    public boolean postMissionVerificate(PostMissionVerficateRequest request) {
        return jpaMissionRepositoryImpl.postMissionVerificate(request.verificaterUserId(), request.verificatedUserId(), request.missionId());
    }
}