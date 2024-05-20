package com.MobileProgramming.service;

import com.MobileProgramming.domain.Verification;
import com.MobileProgramming.dto.request.GetDailyProofsRequest;
import com.MobileProgramming.dto.request.PostMissionProofRequest;
import com.MobileProgramming.dto.request.PostMissionVerficateRequest;
import com.MobileProgramming.dto.response.GetMissionDataResponse;
import com.MobileProgramming.dto.response.GetMissionShortDataResponse;
import com.MobileProgramming.repository.JPA.JPAUserRepositoryImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MissionService {
    private final JPAUserRepositoryImpl jpaUserRepositoryImpl;

    public MissionService(JPAUserRepositoryImpl jpaUserRepositoryImpl) {
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
        if (userId != viewingUserId) {
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

    @Transactional
    public List<Integer> getDailyProof(GetDailyProofsRequest request) {
        //해당월 전체일 리스트 생성
        List<Date> dateList = generateDateList(request.year(), request.month());
        List<Integer> proofDataList = new ArrayList<>();
        int dailyProofCount = 0;

        //각 일 별 proof count 리스트 생성
        for (Date date : dateList) {
            List<Integer> dailyMissionIdList = jpaUserRepositoryImpl.getMissionIdsByuserIdAndDate(request.userId(), date);
            dailyProofCount = 0;
            for (Integer missionId : dailyMissionIdList) {
                System.out.println(missionId);
                if (jpaUserRepositoryImpl.getImageByMissionIdAndUserIdAndDate(request.userId(), missionId, date) != null)
                    dailyProofCount++;
            }
            proofDataList.add(dailyProofCount);
        }
        return proofDataList;
    }

    //해당월 전체일자 리스트 생성
    public List<Date> generateDateList(int year, int month) {
        List<Date> dates = new ArrayList<>();
        // 해당 년월의 월초-월말 날짜 설정
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        while (!start.isAfter(end)) {
            dates.add(Date.valueOf(start));
            start = start.plusDays(1);
        }

        return dates;
    }

    //이미지 업로드
    public void postProofImage(PostMissionProofRequest request){
        jpaUserRepositoryImpl.postProofImage(request.userId(), request.missionId(), request.image());
    }
}