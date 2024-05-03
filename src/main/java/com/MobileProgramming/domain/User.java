package com.MobileProgramming.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name = "\"User\"", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_name", "user_nickname", "user_kakao_email", "user_phone"}, name = "unique_user_info")})
//DB가 수정됨에 따라 수정되는데 제가 위에 꺼 잘 몰라서 일단 주석처리했습니다. 필요한거라면 다시 주석 풀고 맞게 바꾸면 될 것 같아요.
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer UserId;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_nickname", nullable = false)
    private String nickname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
}
