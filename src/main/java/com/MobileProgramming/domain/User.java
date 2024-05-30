package com.MobileProgramming.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Entity
@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "email", unique = true)
    private String email;

    @Builder
    public User(String nickname, String email) {
        this.email = email;
        this.nickname = nickname;
    }

}
