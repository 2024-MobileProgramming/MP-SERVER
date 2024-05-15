package com.MobileProgramming.dto.request;

public record PostMissionVerficateRequest(
        int verificaterUserId,  // 평가하는 유저
        int verificatedUserId, //평가 받는 유저
        int missionId
){
}
