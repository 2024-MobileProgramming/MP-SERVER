package com.MobileProgramming.domain;

import com.MobileProgramming.domain.compositeKey.DailyUpdateID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;

@Entity
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@IdClass(DailyUpdateID.class)
public class DailyUpdate {
    @Id
    @Column(name = "user_id")
    int user_id;

    @Id
    @Column(name = "freind_id")
    int freind_id;

    @Id
    @Column(name = "updated_date")
    Date updated_date;


//    매일 오늘의 친구는 갱신되어야 함. 즉 매일 팀이 바뀌는 개념
//    그렇기 때문에 어떤 user에 대해서 어떤 친구들이 생겼는지 알 수 있는 테이블 정보.
    @Builder
    public DailyUpdate(int user_id, int freind_id, Date Date) {
        this.user_id = user_id;
        this.freind_id = freind_id;
        this.updated_date = Date;
    }
}
