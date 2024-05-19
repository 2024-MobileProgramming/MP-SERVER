package com.MobileProgramming.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorMessage {
    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "회원 정보가 존재하지 않습니다."),
    GOAL_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "목표 정보가 존재하지 않습니다."),
    VERIFICATE_NOT_DONE_EXCEPTION(HttpStatus.NOT_FOUND, "미션은 존재하지만 평가가 기록되지 못했습니다."),
    ALREADY_VERIFICATE_EXCEPTION(HttpStatus.NOT_FOUND, "해당 유저는 이미 평가하였습니다.");
    private final HttpStatus status;
    private final String message;
}
