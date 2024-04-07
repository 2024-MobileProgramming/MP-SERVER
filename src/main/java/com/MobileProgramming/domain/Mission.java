package com.MobileProgramming.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Entity
@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission {
    @Id
    @Column(name = "mission_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer missionId;

    @Column(nullable = false)
    private String content;

    private LocalDateTime createdTime;

    @Builder
    public Mission(String content, LocalDateTime createdTime) {
        this.content = content;
        this.createdTime = createdTime;
    }
}
