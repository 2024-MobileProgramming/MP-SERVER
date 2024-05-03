package com.MobileProgramming.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;

@Entity
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class MissionProof {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proof_id")
    int proof_id;

    @Column(name = "user_id")
    int user_id;

    @Column(name = "mission_id")
    int mission_id;

    @Column(name = "proof_image")
    byte[] image;

    @Column(name = "proof_description")
    String description;

    @Column(name = "proof_date")
    Date date;

    @Builder
    public MissionProof(int user_id, int mission_id, byte[] image, String description, Date date) {
        this.user_id = user_id;
        this.mission_id = mission_id;
        this.image = image;
        this.description = description;
        this.date = date;
    }
}
