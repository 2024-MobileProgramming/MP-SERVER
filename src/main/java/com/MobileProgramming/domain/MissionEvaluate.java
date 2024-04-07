package com.MobileProgramming.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Entity
@Slf4j
@Getter
@Table(name = "UserCheck")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MissionEvaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User submitUser;

    @OneToMany(mappedBy = "user_id")
    @Column(name = "host_id")
    private List<User> evaluator;

    @Column(name = "mission_id")
    private Integer missionId;

    @Column(name = "checked")
    private Boolean checked;
}
