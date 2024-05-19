package com.MobileProgramming.dto.response;

public record GetMissionDataResponse(
        int missionId,
        byte[] image,
        int verificationNumber,//타 사용자가 인증 갯수
        String missionTitle,
        String missionDescription,
        String url
) {
//    public static GetMissionDataResponse of()
}
