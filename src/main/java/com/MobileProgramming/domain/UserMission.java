package com.MobileProgramming.domain;

import com.MobileProgramming.domain.compositeKey.UserMissionID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Entity(name = "USERMISSION") // 엔티티 이름 설정
@Table(name = "user_mission") // 테이블 이름 설정
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@IdClass(UserMissionID.class)
//다른 테이블은 하나의 단어여서 다 소문자로 매칭되지만, 이 테이블은 두개의 단어 조합이라 찾지 못했음.
//해당 클래스는 PK가 여러 속성으로 이루어져 있기에 새로운 클래스(UserMissionID)를 만들고, 그걸로 복합키 설정했습니다.
public class UserMission {
    @Id
    @Column(name = "user_id")
    int userId;

    @Id
    @Column(name = "mission_id")
    int missionId;

//    오늘의 친구에게서 인증받은 횟수
//    MYSQL에서 int default 0 이라고 써져있는데 어떤 추가 작업이 필요할지 안할지 자체를 몰라서 일단 그냥 다른 속성들처럼 매핑했습니다. 추후 오류 뜰 시 작업해야함
    @Column(name = "verification_count")
    int count;

    public UserMission(int userId, int missionId, int count) {
        this.userId = userId;
        this.missionId = missionId;
        this.count = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMission that = (UserMission) o;
        return userId == that.userId && missionId == that.missionId && count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, missionId, count);
    }
}
