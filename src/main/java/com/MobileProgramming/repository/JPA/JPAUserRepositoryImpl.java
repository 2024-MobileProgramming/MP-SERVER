package com.MobileProgramming.repository.JPA;


import com.MobileProgramming.domain.Mission;
import com.MobileProgramming.domain.Team;
import com.MobileProgramming.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static com.MobileProgramming.domain.QMission.mission;
import static com.MobileProgramming.domain.QTeam.team;
import static com.MobileProgramming.domain.QUser.user;

@Slf4j
@Repository
@Transactional
public class JPAUserRepositoryImpl implements JPAUserRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;

    public JPAUserRepositoryImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public void saveUser(User user) {
        em.persist(user);
    }


    //    .fetch()의 결과는 List<User> 형식
    @Override
    public List<User> findUserByName(String name) {
        return query.selectFrom(user)
                .where(user.name.eq(name))
                .fetch();
    }

    @Override
    public List<Mission> getMission() {
        return query.selectFrom(mission)
                .where()
                .fetch();
    }

    @Override
    public List<User> findAll() {
        return query.select(user)
                .from(user)
                .fetch();
    }

    @Override
    public void saveTeam() {
        teamDailyRenewal();

        }

    @Override
    public long teamDailyRenewal() {
        return 0;
    }
}

}
