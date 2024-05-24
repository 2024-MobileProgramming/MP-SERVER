package com.MobileProgramming.dto.response;

public record GetMissionShortDataResponse (
        int missionId,
        String nickname,
        String missionTitle,
        String missionShortDescription,
        boolean imageProofed,
        int verficatedCount
){
}
