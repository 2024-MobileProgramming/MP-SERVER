package com.MobileProgramming.service;

import com.MobileProgramming.dto.response.GetTeamMemberListResponse;
import com.MobileProgramming.repository.JPA.JPAUserRepository;
import jakarta.transaction.Transactional;

import java.util.List;

public class UserService {
    JPAUserRepository jpaUserRepositoryImpl;

    //같은 팀인 멤버 정보 리스트
    @Transactional
    public List<GetTeamMemberListResponse> getTeamMemberList(int userId) {
        List<Integer> teamUserIdList = jpaUserRepositoryImpl.getUserIdsFromTeamByUserId(userId);
        List<GetTeamMemberListResponse> memberDataList = null;
        for (Integer memberId : teamUserIdList) {
            GetTeamMemberListResponse memberData = new GetTeamMemberListResponse(userId, jpaUserRepositoryImpl.getUserNameByUserId(memberId).get(0));
            memberDataList.add(memberData);
        }
        return memberDataList;
    }
}
