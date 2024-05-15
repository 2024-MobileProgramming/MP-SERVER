package com.MobileProgramming.controller;

import com.MobileProgramming.dto.response.GetMissionDataResponse;
import com.MobileProgramming.service.MissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mission")
public class MissionController {
    private MissionService missionService;

    //미션 1개에 대해 미션에 대한 데이터 get
    //mission/userId/missionId
    @GetMapping("/{userId}/{missionId}")
    public ResponseEntity<GetMissionDataResponse> getMissionData(@PathVariable int userId, int missionId) throws Exception {
        return ResponseEntity.ok(missionService.getMissionData(userId, missionId));
    }

}
