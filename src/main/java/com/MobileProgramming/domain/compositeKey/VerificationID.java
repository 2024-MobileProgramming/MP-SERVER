package com.MobileProgramming.domain.compositeKey;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class VerificationID implements Serializable {
    private int userId;
    private int missionId;
    private int verifierId;
}
