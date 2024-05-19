package com.MobileProgramming.repository.JPA;

import com.MobileProgramming.domain.*;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

import static com.MobileProgramming.domain.QMission.mission;
import static com.MobileProgramming.domain.QMissionProof.missionProof;
import static com.MobileProgramming.domain.QTeam.team;
import static com.MobileProgramming.domain.QUser.user;
import static com.MobileProgramming.domain.QUserMission.userMission;
import static com.MobileProgramming.domain.QVerification.verification;

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
    public List<Mission> getAllMission() {
        return query.selectFrom(mission)
                .fetch();
    }

    @Override
    public List<User> findAllUser() {
        return query.selectFrom(user)
                .fetch();
    }

    @Override
    public List<Integer> getTeamSize() {
        return query.select(team.teamId).from(team).fetch();
    }

    @Override
    public void saveTeam(Team team) {
        em.persist(team);
    }

    @Override
    public List<Team> getAllTeamInfo() {
        return query.selectFrom(team)
                .fetch();
    }

    @Override
    public List<Integer> getUserIdsFromTeamByUserId(int userId) {
        QTeam team = QTeam.team;
        return query.select(team.userId)
                .from(team)
                .where(team.teamId.eq(
                        JPAExpressions.select(team.teamId)
                                .from(team)
                                .where(team.userId.eq(userId))
                ))
                .fetch();
    }


    @Override
    public List<Integer> getTeamIdByUserId(int UserId) {
        return query.select(team.teamId)
                .from(team)
                .where(team.userId.eq(UserId))
                .fetch();
    }

    //유저 아이디, 날짜데이터로 할당받은 미션 아이디 받아오기
    public List<Integer> getMissionIdsByuserIdAndDate(int userId, Date date) {
        QUserMission userMission = QUserMission.userMission;
        DatePath<Date> updateDatePath = Expressions.datePath(Date.class, userMission, "updateDate");
        return query.select(userMission.missionId)
                .from(userMission)
                .where(userMission.userId.eq(userId)
                        .and(updateDatePath.eq(date)))
                .fetch();
    }

    //미션 아이디로 미션 장문 설명 get
    @Override
    public List<String> getMissionDescriptionByMissionId(int MissionId) {
        return query
                .select(mission.description)
                .from(mission)
                .where(mission.missionId.eq(MissionId))
                .fetch();
    }

    //미션 아이디로 미션 단문 설명글 get
    @Override
    public String getShortDescriptionByMissionId(int MissionId) {
        return query
                .select(mission.shortDescription)
                .from(mission)
                .where(mission.missionId.eq(MissionId))
                .fetchFirst();
    }

    //미션 아이디로 미션 url get
    @Override
    public String getMissionUrlByMissionId(int MissionId) {
        return query
                .select(mission.url)
                .from(mission)
                .where(mission.missionId.eq(mission.missionId))
                .fetchFirst();
    }

    //미션 아이디로 미션 단문 설명글 get
    @Override
    public byte[] getImageByMissionId(int userId, int MissionId) {
        Date currentDate = new Date(System.currentTimeMillis());
        return query
                .select(missionProof.image)
                .from(missionProof)
                .where(missionProof.missionId.eq(MissionId)
                        .and(missionProof.date.eq(currentDate))
                        .and(missionProof.userId.eq(userId)))
                .fetchFirst();
    }

    //특정 유저의 특정 미션 승인 횟수 get
    @Override
    public int getMissionVerificationCountByMissionIdAndUserId(int missionId, int userId) {
        Date currentDate = new Date(System.currentTimeMillis());

        Integer verificateCount = query.select(userMission.count)
                .from(userMission)
                .where(userMission.userId.eq(userId)
                        .and(userMission.missionId.eq(missionId))
                        .and(userMission.updateDate.eq(currentDate)))
                .fetchFirst();
        return verificateCount == null ? -1 : verificateCount;
    }


    //미션아이디1개로 해당 미션 모든 데이터 get
    @Override
    public List<Mission> getAllMissionInformationByMissionId(int MissionId) {
        return query
                .selectFrom(mission)
                .where(mission.missionId.eq(MissionId))
                .fetch();
    }

    @Override
    public void allocation(UserMission userMission) {
        em.persist(userMission);
    }

    @Override
    public void verification(int UserId1, int UserId2, int MissionId) {
        Date currentDate = new Date(System.currentTimeMillis());
        Verification verification = new Verification(UserId1, MissionId, UserId2, currentDate);
        em.persist(verification);

        // 쿼리에서 usermission을 UserMission으로 수정
        String queryString = "UPDATE USERMISSION SET count = count + 1 WHERE userId = :userId AND missionId = :missionId";
        Query query = em.createQuery(queryString);
        query.setParameter("userId", UserId1); // UserId1으로 수정
        query.setParameter("missionId", MissionId); // MissionId로 수정
        query.executeUpdate();
    }

    //미션아이디, 유저아이디로 승인 여부 확인
    public List<Verification> getVerifierIDByIdAndMissionId(int userId, int missionId) {
        // userId와 missionId에 해당하는 verification 레코드들을 조회하여 반환
        return query.selectFrom(verification)
                .where(verification.userId.eq(userId).and(verification.missionId.eq(missionId)))
                .fetch();
    }

    @Override
    public List<Integer> getUserIdsByTeamId(int teamId) {
        return query.select(team.userId)
                .from(team)
                .where(team.teamId.eq(teamId))
                .fetch();
    }

    @Override
    public List<String> getUserNameByUserId(int userId) {
        return query.select(user.name)
                .from(user)
                .where(user.userId.eq(userId))
                .fetch();
    }

    @Override
    public List<String> getMissionTitleByMissionId(int missionId) {
        return query.select(mission.missionTitle)
                .from(mission)
                .where(mission.missionId.eq(missionId))
                .fetch();
    }

    @Override
    public List<Verification> getVerificateByUserIdAndMissionIdAndDate(int userId, int missionId, Date date) {
        // userId와 missionId, date에 해당하는 verification 레코드들을 조회하여 반환
        return query.selectFrom(verification)
                .where(verification.userId.eq(userId)
                        .and(verification.missionId.eq(missionId))
                        .and(verification.verifyDate.eq(date)))
                .fetch();
    }
}