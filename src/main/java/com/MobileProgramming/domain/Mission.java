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

    @Column(name = "mission_title")
    private String missionTitle;

    @Override
    public String toString() {
        return "Mission{" +
                "missionId=" + missionId +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", missionTitle='" + missionTitle + '\'' +
                '}';
    }

    public Integer getMissionId() {
        return missionId;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getMissionTitle() {
        return missionTitle;
    }

    public void setMissionTitle(String missionTitle) {
        this.missionTitle = missionTitle;
    }
}
