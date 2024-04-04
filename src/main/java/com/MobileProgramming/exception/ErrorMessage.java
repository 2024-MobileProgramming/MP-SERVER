package com.MobileProgramming.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorMessage {
    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "회원 정보가 존재하지 않습니다."),
    GOAL_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "목표 정보가 존재하지 않습니다.");
    private final HttpStatus status;
    private final String message;
}
