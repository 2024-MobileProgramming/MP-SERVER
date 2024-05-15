package com.MobileProgramming.service;

import com.MobileProgramming.dto.response.GetMissionDataResponse;
import com.MobileProgramming.repository.JPA.MissionRepositoryImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import static com.MobileProgramming.exception.ErrorMessage.GOAL_NOT_FOUND_EXCEPTION;

@Service
@Transactional
public class MissionService {
    private final MissionRepositoryImpl jpaMissionRepository;

    public MissionService(MissionRepositoryImpl jpaMissionRepository) {
        this.jpaMissionRepository = jpaMissionRepository;
    }

    //특정유저의 1개의 미션에 대한 정보 get method
    @Transactional
    public GetMissionDataResponse getMissionData(int userId, int missionId) throws Exception {
        if (jpaMissionRepository.getMissionData(userId, missionId) == null)
            throw new Exception(GOAL_NOT_FOUND_EXCEPTION.getMessage());
            return jpaMissionRepository.getMissionData(userId, missionId);
    }
}