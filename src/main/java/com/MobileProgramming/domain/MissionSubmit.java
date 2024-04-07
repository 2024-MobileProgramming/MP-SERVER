package com.MobileProgramming.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@Getter
@Table(name = "Part_miss")
@NoArgsConstructor
public class MissionSubmit {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User submittedUser;

    @Id
    @ManyToOne
    @JoinColumn(name = "mission_id", referencedColumnName = "mission_id")
    private Mission mission;

    @Column(name = "mission_photo")
    private String photo;

    @Column(name = "checked_count")
    private Integer approveCount;

    @Builder
    public MissionSubmit(User user, Mission mission, String photo) {
        this.submittedUser = user;
        this.mission = mission;
        this.photo = photo;
        this.approveCount = 0;
    }

    //유저가 인증 사진 업로드
    public void postPhoto(String photo) {
        this.photo = photo;
    }

    //미션 통과 횟수 +1
    public void updateApproveCount() {
        this.approveCount++;
    }
}
