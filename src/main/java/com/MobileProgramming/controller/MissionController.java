package com.MobileProgramming.controller;

import com.MobileProgramming.dto.request.PostMissionVerficateRequest;
import com.MobileProgramming.dto.response.GetMissionDataResponse;
import com.MobileProgramming.dto.response.GetMissionShortDataResponse;
import com.MobileProgramming.exception.ErrorMessage;
import com.MobileProgramming.exception.SuccessMessage;
import com.MobileProgramming.global.response.ApiResponse;
import com.MobileProgramming.service.MissionService;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{userId}/{missionId}")
    public ApiResponse<GetMissionDataResponse> getMissionData(@PathVariable int userId, @PathVariable int missionId) throws Exception {
        if (missionService.getMissionData(userId, missionId) == null)
            return ApiResponse.error(ErrorMessage.GOAL_NOT_FOUND_EXCEPTION);
        else
            return ApiResponse.success(SuccessMessage.MISSION_DATA_GET_SUCCESS, missionService.getMissionData(userId, missionId));
    }

    //특정 유저의 특정미션 verificate post
    //인증 post
    @PostMapping("/verificate")
    public ApiResponse postMissionVerificate(@RequestBody PostMissionVerficateRequest request) {
        missionService.postMissionVerificate(request);
        if (missionService.getVerificate(request))
            return ApiResponse.success(SuccessMessage.VERIFICATE_MISSION_SUCCESS);
        else return ApiResponse.error(ErrorMessage.CANNOT_VERIFICATE_EXCEPTION);
    }

    //할당받은 미션의 간략데이터 리스트 get
    @GetMapping("/{userId}")
    public ApiResponse<List<GetMissionShortDataResponse>> getTodayMissionList(@PathVariable int userId) {
        //오늘 할당받은 미션아이디 리스트 받아오기
        List<Integer> missionIdList = missionService.getMissionIdList(userId);
        if (missionIdList == null)
            return ApiResponse.error(ErrorMessage.USER_NOT_FOUND_EXCEPTION);

        List<GetMissionShortDataResponse> missionDataList = null;

        //for문으로 할당받은 미션별 데이터 가져오기
        for (Integer missionId : missionIdList) {
            GetMissionShortDataResponse missionShortData = missionService.getMissionShortData(userId, missionId);
            missionDataList.add(missionShortData);
        }
        if (missionDataList != null)
            return ApiResponse.success(SuccessMessage.TODAY_MISSIONS_GET_SUCCESS, missionDataList);
        else return ApiResponse.error(ErrorMessage.GOAL_NOT_FOUND_EXCEPTION);
    }

}
