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

    @Column(name="mission_site_url")
    private String url;

    @Column(name = "short_description")
    private String shortDescription;

    @Override
    public String toString() {

        return "Mission{" +
                "missionId=" + missionId +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mission mission = (Mission) o;
        return Objects.equals(missionId, mission.missionId) && Objects.equals(description, mission.description) && Objects.equals(url, mission.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(missionId, description, url);
    }
}
