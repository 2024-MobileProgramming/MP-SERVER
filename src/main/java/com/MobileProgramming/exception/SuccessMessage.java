package com.MobileProgramming.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessMessage {
    USER_CREATE_SUCCESS(HttpStatus.CREATED, "사용자 정보가 생성되었습니다."),
    USER_SEARCH_SUCCESS(HttpStatus.OK, "사용자 정보가 조회되었습니다."),
    ALL_USER_SEARCH_SUCCESS(HttpStatus.NO_CONTENT, "사용자 전체 정보가 조회되었습니다."),
    USER_UPDATE_SUCCESS(HttpStatus.NO_CONTENT, "사용자 정보가 업데이트되었습니다."),
    USER_DELETE_SUCCESS(HttpStatus.NO_CONTENT, "사용자 정보가 삭제되었습니다.");

    private final HttpStatus status;
    private final String message;
}
