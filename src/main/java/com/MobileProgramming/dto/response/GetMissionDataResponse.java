package com.MobileProgramming.dto.response;

public record GetMissionDataResponse(
        int missionId,
        int verificationNumber,//타 사용자가 인증 갯수
        String missionDescription,
        byte[] image
) {
//    public static GetMissionDataResponse of()
}
