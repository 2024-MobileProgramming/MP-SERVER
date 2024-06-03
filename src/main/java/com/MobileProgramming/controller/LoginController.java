package com.MobileProgramming.controller;


import com.MobileProgramming.domain.User;
import com.MobileProgramming.dto.request.LoginRequestDto;
import com.MobileProgramming.dto.response.LoginResponseDto;
import com.MobileProgramming.exception.SuccessMessage;
import com.MobileProgramming.global.response.ApiResponse;
import com.MobileProgramming.repository.JPA.JPAUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private JPAUserRepository jpaUserRepository;

    @PostMapping
    public ApiResponse<LoginResponseDto> login(@RequestBody LoginRequestDto UserInfo) {
        String email = UserInfo.getEmail();
        String nickname = UserInfo.getNickname();
        List<Integer> userIdByEmail = jpaUserRepository.findUserIdByEmail(email);
        if (userIdByEmail.isEmpty()) {
            jpaUserRepository.saveUser(new User(nickname, email));

        }
        return ApiResponse.success(SuccessMessage.USER_CREATE_SUCCESS, new LoginResponseDto(jpaUserRepository.findUserIdByEmail(email).get(0)));

    }


}
