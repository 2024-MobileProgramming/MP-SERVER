package com.MobileProgramming.domain;

import com.MobileProgramming.domain.compositeKey.VerificationID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@IdClass(VerificationID.class)
public class Verification {
    @Id
    @Column(name = "user_id")
    int UserId;

    @Id
    @Column(name = "mission_id")
    int mission_id;

    @Id
    @Column(name = "verifier_id")
    int verifier_id;

    @Builder
    public Verification(int user,  int mission_id, int evaluater) {
        this.UserId = user;
        this.mission_id = mission_id;
        this.verifier_id = evaluater;
    }
}


