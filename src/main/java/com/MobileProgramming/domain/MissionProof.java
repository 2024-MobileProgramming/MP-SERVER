package com.MobileProgramming.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class MissionProof {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proof_id")
    int proofId;

    @Column(name = "user_id")
    int userId;

    @Column(name = "mission_id")
    int missionId;

    @Column(name = "proof_image")
    byte[] image;

    @Column(name = "proof_description")
    String description;

    @Column(name = "proof_date")
    Date date;

    @Builder
    public MissionProof(int user_id, int mission_id, byte[] image, String description, Date date) {
        this.userId = user_id;
        this.missionId = mission_id;
        this.image = image;
        this.description = description;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MissionProof that = (MissionProof) o;
        return proofId == that.proofId && userId == that.userId && missionId == that.missionId && Arrays.equals(image, that.image) && Objects.equals(description, that.description) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(proofId, userId, missionId, description, date);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
