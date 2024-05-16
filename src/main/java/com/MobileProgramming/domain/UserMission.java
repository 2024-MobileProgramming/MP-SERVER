package com.MobileProgramming.domain;

import com.MobileProgramming.domain.compositeKey.UserMissionID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "USERMISSION") // 엔티티 이름 설정
@Table(name = "user_mission") // 테이블 이름 설정
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@IdClass(UserMissionID.class)

public class UserMission {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Id
    @Column(name = "mission_id")
    private int missionId;

    @Id
    @Column(name="updated_date")
    private Date updateDate;

    @Column(name = "verification_count")
    int count;

    public UserMission(int userId, int missionId, int count, Date date) {
        this.userId = userId;
        this.missionId = missionId;
        this.count = 0;
        this.updateDate = date;
    }

    @Override
    public String toString() {
        return "UserMission{" +
                "userId=" + userId +
                ", missionId=" + missionId +
                ", updateDate=" + updateDate +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMission that = (UserMission) o;
        return userId == that.userId && missionId == that.missionId && count == that.count && Objects.equals(updateDate, that.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, missionId, updateDate, count);
    }
}
