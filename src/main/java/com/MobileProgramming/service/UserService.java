package com.MobileProgramming.service;

import com.MobileProgramming.dto.response.GetTeamMemberListResponse;
import com.MobileProgramming.repository.JPA.JPAUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    JPAUserRepository jpaUserRepositoryImpl;

    public UserService(JPAUserRepository jpaUserRepositoryImpl) {
        this.jpaUserRepositoryImpl = jpaUserRepositoryImpl;
    }


    //같은 팀인 멤버 정보 리스트
    @Transactional
    public List<GetTeamMemberListResponse> getTeamMemberList(int userId) {
        List<Integer> teamUserIdList = jpaUserRepositoryImpl.getUserIdsFromTeamByUserId(userId);
        List<GetTeamMemberListResponse> memberDataList = new ArrayList<>();
        for (Integer memberId : teamUserIdList) {
            GetTeamMemberListResponse memberData = new GetTeamMemberListResponse(memberId, jpaUserRepositoryImpl.getUserNameByUserId(memberId).get(0));
            memberDataList.add(memberData);
        }
        return memberDataList;
    }
}
