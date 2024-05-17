package com.MobileProgramming.controller;

import com.MobileProgramming.dto.response.GetTeamMemberListResponse;
import com.MobileProgramming.service.UserService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<GetTeamMemberListResponse>> getTeamMemberList(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getTeamMemberList(userId));
    }
}
