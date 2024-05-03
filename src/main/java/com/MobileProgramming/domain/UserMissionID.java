package com.MobileProgramming.domain;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class UserMissionID implements Serializable {
    private int user_id;
    private int mission_id;
}
