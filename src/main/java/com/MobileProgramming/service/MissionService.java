package com.MobileProgramming.service;

import com.MobileProgramming.domain.Verification;
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
    public GetMissionDataResponse getMissionData(int userId, int missionId, int viewingUserId) throws Exception {
        //미션할당자, 미션아이디 맞는 쌍인지 확인
        System.out.println(jpaUserRepositoryImpl.getMissionVerificationCountByMissionIdAndUserId(missionId, userId));
        if (jpaUserRepositoryImpl.getMissionVerificationCountByMissionIdAndUserId(missionId, userId) == -1)
            return null;
        List<Verification> verificationList = jpaUserRepositoryImpl.getVerificateByUserIdAndMissionIdAndDate(userId, missionId, new Date(System.currentTimeMillis()));
        boolean verificatedByViewingUser = false;

        //해당 미션 뷰 조회자가 해당 미션 평가했는지 여부 확인
        if(userId != viewingUserId) {
            for (Verification verification : verificationList) {
                if (verification.getVerifierId() == viewingUserId)
                    verificatedByViewingUser = true; //조회한 유저가 이미 평가한 경우
            }
        }

        System.out.println(missionId);
        // 카운트, 이미지 받아오기, 장문 설명, url, 조회자가 평가 여부 받아오기
        return new GetMissionDataResponse(missionId,
                jpaUserRepositoryImpl.getImageByMissionId(userId, missionId),
                jpaUserRepositoryImpl.getMissionVerificationCountByMissionIdAndUserId(missionId, userId),
                jpaUserRepositoryImpl.getMissionTitleByMissionId(missionId).get(0),
                jpaUserRepositoryImpl.getMissionDescriptionByMissionId(missionId).get(0),
                jpaUserRepositoryImpl.getMissionUrlByMissionId(missionId),
                verificatedByViewingUser);
    }

    //특정 유저의 특정미션 verification 하기
    @Transactional
    public void postMissionVerificate(PostMissionVerficateRequest request) {
        jpaUserRepositoryImpl.verification(request.verificatedUserId(), request.verificaterUserId(), request.missionId());
    }

    //날짜 반영해서 get 되도록 수정필요*****
    //승인한 상황일 때 true 리턴
    @Transactional
    public boolean getVerificate(PostMissionVerficateRequest request, Date date) {
        //이미 verificate된 경우 true 리턴, unverificate일 때 false 리턴
        List<Verification> verificationList = jpaUserRepositoryImpl.getVerificateByUserIdAndMissionIdAndDate(request.verificatedUserId(), request.missionId(), date);
        for (Verification row : verificationList) {
            if (row.getVerifierId() == request.verificaterUserId())
                return true;
        }
        return false;
    }

    //특정 유저의 특정 미션 단문 데이터 get
    @Transactional
    public GetMissionShortDataResponse getMissionShortData(int userId, int missionId) {
        return new GetMissionShortDataResponse(missionId,
                jpaUserRepositoryImpl.getMissionTitleByMissionId(missionId).get(0),
                jpaUserRepositoryImpl.getShortDescriptionByMissionId(missionId),
                jpaUserRepositoryImpl.getImageByMissionId(userId, missionId) == null ? false : true,
                jpaUserRepositoryImpl.getMissionVerificationCountByMissionIdAndUserId(missionId, userId));

    }

    //유저가 할당받은 미션 아이디 리스트 받아내기
    @Transactional
    public List<Integer> getMissionIdList(int userId) {
        return jpaUserRepositoryImpl.getMissionIdsByuserIdAndDate(userId, new Date(System.currentTimeMillis()));
    }
}