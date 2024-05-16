package com.MobileProgramming.domain.compositeKey;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class UserMissionID implements Serializable {
    private int userId;
    private int missionId;
    private Date updateDate;
}
