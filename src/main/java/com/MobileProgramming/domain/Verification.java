package com.MobileProgramming.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@IdClass(UserMissionID.class)
public class Verification {
}

@Builder

