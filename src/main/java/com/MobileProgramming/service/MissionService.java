package com.MobileProgramming.service;

import com.MobileProgramming.dto.request.PostMissionVerficateRequest;
import com.MobileProgramming.dto.response.GetMissionDataResponse;
import com.MobileProgramming.dto.response.GetMissionShortDataResponse;
import com.MobileProgramming.repository.JPA.JPAUserRepositoryImpl;
import com.MobileProgramming.repository.JPA.MissionRepositoryImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

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

    //특정 유저의 특정미션 verification 하기
    @Transactional
    public void postMissionVerificate(PostMissionVerficateRequest request) {
        jpaUserRepositoryImpl.verification(request.verificatedUserId(), request.verificaterUserId(), request.missionId());
    }

    //날짜 반영해서 get 되도록 수정필요*****
    //승인한 상황일 때 true 리턴
    @Transactional
    public boolean getVerificate(PostMissionVerficateRequest request){
        return jpaUserRepositoryImpl.getVerifierIDByIdAndMissionId(request.verificatedUserId(), request.missionId())!=null;
    }

    //특정 유저의 특정 미션 단문 데이터 get
    @Transactional
    public GetMissionShortDataResponse getMissionShortData(int userId, int missionId) {
        List<Integer> missionList = getMissionIdList(userId);
        List<GetMissionShortDataResponse> list = null;
        return new GetMissionShortDataResponse(missionId,
                jpaUserRepositoryImpl.getMissionTitleByMissionId(missionId).get(0),
                jpaUserRepositoryImpl.getShortDescriptionByMissionId(missionId),
                jpaUserRepositoryImpl.getImageByMissionId(userId, missionId) == null ? false : true,
                jpaUserRepositoryImpl.getMissionVerificationCountByMissionIdAndUserId(missionId, userId) == 0 ? false : true);

    }

    //유저가 할당받은 미션 아이디 리스트 받아내기
    @Transactional
    public List<Integer> getMissionIdList(int userId) {
        return jpaUserRepositoryImpl.getMissionIdsByuserIdAndDate(userId, new Date(System.currentTimeMillis()));
    }
}