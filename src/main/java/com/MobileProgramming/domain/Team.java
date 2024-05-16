package com.MobileProgramming.domain;

import com.MobileProgramming.domain.compositeKey.TeamID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.util.Objects;

@Entity
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@IdClass(TeamID.class)
public class Team {
    @Id
    @Column(name = "user_id")
    private int userId;

    @Id
    @Column(name = "team_id")
    private int teamId;

    @Id
    @Column(name = "updated_date")
    private Date updatedDate;


    //    매일 오늘의 친구는 갱신되어야 함. 즉 매일 팀이 바뀌는 개념
//    그렇기 때문에 어떤 user에 대해서 어떤 친구들이 생겼는지 알 수 있는 테이블 정보.
    @Builder
    public Team(int userId, int teamId, Date updatedDate) {
        this.userId = userId;
        this.teamId = teamId;
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Team{" +
                "userId=" + userId +
                ", teamId=" + teamId +
                ", updatedDate=" + updatedDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team that = (Team) o;
        return userId == that.userId && teamId == that.teamId && Objects.equals(updatedDate, that.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, teamId, updatedDate);
    }
}
