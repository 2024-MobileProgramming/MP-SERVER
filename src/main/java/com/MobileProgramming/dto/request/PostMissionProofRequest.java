package com.MobileProgramming.dto.request;

public record PostMissionProofRequest(
        int userId,
        int missionId,
        String image
) {
}
