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
@Table(name = "\"User\"", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_name", "user_nickname", "user_kakao_email", "user_phone"}, name = "unique_user_info")})
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer UserId;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_nickname", nullable = false)
    private String nickname;

    @Column(name = "user_profile")
    private String profile;

    @Column(name = "user_kakao_email", unique = true)
    private String email;

    @Column(name = "user_birthday")
    private String birthday;

    @Column(name = "user_birthyear")
    private String birthyear;

    @Column(name = "user_phone", unique = true)
    private String phone;
}
