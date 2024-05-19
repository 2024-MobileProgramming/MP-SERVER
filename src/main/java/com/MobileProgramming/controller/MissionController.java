package com.MobileProgramming.controller;

import com.MobileProgramming.dto.request.PostMissionVerficateRequest;
import com.MobileProgramming.dto.response.GetMissionDataResponse;
import com.MobileProgramming.dto.response.GetMissionShortDataResponse;
import com.MobileProgramming.exception.ErrorMessage;
import com.MobileProgramming.exception.SuccessMessage;
import com.MobileProgramming.global.response.ApiResponse;
import com.MobileProgramming.service.MissionService;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mission")
public class MissionController {
    private MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    //미션 1개에 대해 미션에 대한 데이터 get
    //미션상세페이지 뷰
    //mission/userId/missionId
    @GetMapping("/{userId}/{missionId}/{viewingUserId}")
    public ApiResponse<GetMissionDataResponse> getMissionData(@PathVariable int userId, @PathVariable int missionId, @PathVariable int viewingUserId) throws Exception {
        GetMissionDataResponse missionData =missionService.getMissionData(userId, missionId, viewingUserId);
        if (missionData == null)
            return ApiResponse.error(ErrorMessage.GOAL_NOT_FOUND_EXCEPTION);
        else
            return ApiResponse.success(SuccessMessage.MISSION_DATA_GET_SUCCESS, missionData);
    }

    //특정 유저의 특정미션 verificate post
    //인증 post
    @PostMapping("/verificate")
    public ApiResponse postMissionVerificate(@RequestBody PostMissionVerficateRequest request) {
        //승인여부 확인하고 post 실행
        Date date = new Date(System.currentTimeMillis());
        if (!missionService.getVerificate(request, date)) {
            missionService.postMissionVerificate(request); //승인 실행
            //정상적으로 승인 post 완료시
            if (missionService.getVerificate(request, date))
                return ApiResponse.success(SuccessMessage.VERIFICATE_MISSION_SUCCESS);
            else return ApiResponse.error(ErrorMessage.VERIFICATE_NOT_DONE_EXCEPTION);
        } else return ApiResponse.error(ErrorMessage.ALREADY_VERIFICATE_EXCEPTION);
    }

    //할당받은 미션의 간략데이터 리스트 get
    @GetMapping("/{userId}")
    public ApiResponse<List<GetMissionShortDataResponse>> getTodayMissionList(@PathVariable int userId) {
        //오늘 할당받은 미션아이디 리스트 받아오기
        List<Integer> missionIdList = missionService.getMissionIdList(userId);
        if (missionIdList == null)
            return ApiResponse.error(ErrorMessage.USER_NOT_FOUND_EXCEPTION);
        List<GetMissionShortDataResponse> missionDataList = new ArrayList<>();

        //for문으로 할당받은 미션별 데이터 가져오기
        for (Integer missionId : missionIdList) {
            GetMissionShortDataResponse missionShortData = missionService.getMissionShortData(userId, missionId);
            missionDataList.add(missionShortData);
        }
        if (missionDataList != null)
            return ApiResponse.success(SuccessMessage.TODAY_MISSIONS_GET_SUCCESS, missionDataList);
        else return ApiResponse.error(ErrorMessage.GOAL_NOT_FOUND_EXCEPTION);
    }

    //년-달, 유저id 정보 받으면 int array로 리턴하기
    //해당 달, 유저가 매일 할당받은 미션들 중, proof이미지 올린 미션갯수 count == 1일 미션 수행 정보 -> array로 만들어
    @GetMapping("/monthly")
    public ApiResponse<List<Integer>> getDailyProofedMissions(@RequestBody GetDailyProofsRequest request) {
        return ApiResponse.success(SuccessMessage.DAILY_PROOF_GET_SUCCESS, missionService.getDailyProof(request));
    }

}
