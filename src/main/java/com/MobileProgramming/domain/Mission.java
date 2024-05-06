package com.MobileProgramming.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Entity
@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission {
    @Id
    @Column(name = "mission_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer missionId;

    @Column(name="description")
    private String description;

//    이 클래스는 단순히 Database에 이미 저장되어 있는 미션들을 꺼내오기 위한 용도로 Insert 할 것이 없고, 단순히 데이터베이스에서 가져오는 역할만 할 것 같아서 Bulder 제외했는데
//    필요할지 안할지는 잘 모르겠습니다.


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mission mission = (Mission) o;
        return Objects.equals(missionId, mission.missionId) && Objects.equals(description, mission.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(missionId, description);
    }

    @Override
    public String toString() {
        return "Mission{" +
                "missionId=" + missionId +
                ", description='" + description + '\'' +
                '}';
    }
}
