package com.MobileProgramming.controller;

import com.MobileProgramming.dto.request.PostMissionVerficateRequest;
import com.MobileProgramming.dto.response.GetMissionDataResponse;
import com.MobileProgramming.dto.response.GetMissionShortDataResponse;
import com.MobileProgramming.exception.ErrorMessage;
import com.MobileProgramming.service.MissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mission")
public class MissionController {
    private MissionService missionService;

    //미션 1개에 대해 미션에 대한 데이터 get
    //미션상세페이지 뷰
    //mission/userId/missionId
    @GetMapping("/{userId}/{missionId}")
    public ResponseEntity<GetMissionDataResponse> getMissionData(@PathVariable int userId, @PathVariable int missionId) throws Exception {
        return ResponseEntity.ok(missionService.getMissionData(userId, missionId));
    }

    //특정 유저의 특정미션 verificate post
    //인증 post
    @PostMapping("/verificate")
    public ResponseEntity postMissionVerificate(@RequestBody PostMissionVerficateRequest request) throws Exception {
        if (missionService.postMissionVerificate(request))
            return ResponseEntity.noContent().build();
        else throw new Exception(ErrorMessage.CANNOT_VERIFICATE_EXCEPTION.getMessage());
    }

    //할당받은 미션의 간략데이터 리스트 get
    @GetMapping("/{userId}")
    public ResponseEntity<List<GetMissionShortDataResponse>> getTodayMissionList(@PathVariable int userId) {
        //오늘 할당받은 미션아이디 리스트 받아오기
        List<Integer> missionIdList = missionService.getMissionIdList(userId);

        List<GetMissionShortDataResponse> missionDataList = null;

        //for문으로 할당받은 미션별 데이터 가져오기
        for (Integer missionId : missionIdList) {
            GetMissionShortDataResponse missionShortData = missionService.getMissionShortData(userId, missionId);
            missionDataList.add(missionShortData);
        }
        return ResponseEntity.ok(missionDataList);
    }

}
