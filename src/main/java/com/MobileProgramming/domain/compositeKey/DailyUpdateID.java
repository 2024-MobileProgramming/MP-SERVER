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
public class DailyUpdateID implements Serializable {
    int user_id;
    int friend_id;
    Date update_date;
}
