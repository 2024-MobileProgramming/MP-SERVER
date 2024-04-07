package com.MobileProgramming.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@Getter
@Table(name = "UserCheck")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MissionEvaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User submitUser;

    @ManyToOne
    @JoinColumn(name = "host_id", referencedColumnName = "user_id")
    private User evaluator;

    @Column(name = "mission_id")
    private Integer missionId;

    @Column(name = "checked")
    private Boolean approval;

    @Builder
    public MissionEvaluate(User user, User evaluator, Integer missionId) {
        this.submitUser = user;
        this.evaluator = evaluator;
        this.missionId = missionId;
        this.approval = false;
    }

    //평가유저가 미션 성공으로 판단시
    public void updateApproval() {
        approval = true;
    }
}
