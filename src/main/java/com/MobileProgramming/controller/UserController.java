package com.MobileProgramming.controller;

import com.MobileProgramming.dto.response.GetTeamMemberListResponse;
import com.MobileProgramming.exception.ErrorMessage;
import com.MobileProgramming.exception.SuccessMessage;
import com.MobileProgramming.global.response.ApiResponse;
import com.MobileProgramming.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    //특정 유저 아이디로 같은 팀멤버리스트(닉네임, 아이디 get)
    //친구목록view
    @GetMapping("/memberList/{userId}")
    public ApiResponse<List<GetTeamMemberListResponse>> getTeamMemberList(@PathVariable int userId) {
        if (userService.getTeamMemberList(userId) == null)
            return ApiResponse.error(ErrorMessage.USER_NOT_FOUND_EXCEPTION);
        else return ApiResponse.success(SuccessMessage.TEAM_MEMBER_GET_SUCCESS, userService.getTeamMemberList(userId));
    }
}
