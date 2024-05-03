package com.MobileProgramming.domain;

import com.MobileProgramming.domain.compositeKey.UserMissionID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@IdClass(UserMissionID.class)
//해당 클래스는 PK가 여러 속성으로 이루어져 있기에 새로운 클래스(UserMissionID)를 만들고, 그걸로 복합키 설정했습니다.
public class UserMission {
    @Id
    @Column(name = "user_id")
    int UserId;

    @Id
    @Column(name = "mission_id")
    int missionId;

//    오늘의 친구에게서 인증받은 횟수
//    MYSQL에서 int default 0 이라고 써져있는데 어떤 추가 작업이 필요할지 안할지 자체를 몰라서 일단 그냥 다른 속성들처럼 매핑했습니다. 추후 오류 뜰 시 작업해야함
    @Column(name = "verification_count")
    int count;
}
