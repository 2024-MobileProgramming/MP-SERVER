package com.MobileProgramming.repository.JPA;

import com.MobileProgramming.domain.Verification;
import com.MobileProgramming.dto.response.GetMissionDataResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

import static com.MobileProgramming.domain.QMission.mission;
import static com.MobileProgramming.domain.QMissionProof.missionProof;
import static com.MobileProgramming.domain.QUserMission.userMission;

@Slf4j
@Repository
@Transactional
public class MissionRepositoryImpl implements MissionRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;

    @Autowired
    public MissionRepositoryImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public GetMissionDataResponse getMissionData(int userId, int missionId) {

        LocalDate localDate = LocalDate.now();
        Date currentDate = Date.valueOf(localDate);
        //int verificationNumber;
        /*if(query.select(userMission.count).from(userMission).where(userMission.missionId.eq(missionId), userMission.userId.eq(userId)).fetchFirst()==null)
        {
            verificationNumber = -1;
        }else {
            verificationNumber = query.select(userMission.count).from(userMission).where(userMission.missionId.eq(missionId), userMission.userId.eq(userId)).fetchFirst();
        }*/
        //인증자 0명시 쿼리가 null 리턴 -> verification 값에 -1 넣는 것으로 처리 (java에서 int 값에 nullx)
        int verificationNumber = query.select(userMission.count).from(userMission).where(userMission.missionId.eq(missionId), userMission.userId.eq(userId)).fetchFirst() == null ? -1 : query.select(userMission.count).from(userMission).where(userMission.missionId.eq(missionId), userMission.userId.eq(userId)).fetchFirst();


        //String description = query.select(mission.description).from(mission).where(mission.missionId.eq(missionId));
        String description = query.select(mission.description).from(mission)
                .where(mission.missionId.eq(missionId))
                .fetchFirst();
        //String missionImage = query.select(missionProof.image).from(missionProof).where(missionProof.missionId.eq(missionId), missionProof.userId.eq(userId), missionProof.date.eq(currentDate)).fetchFirst();
        byte[] missionImage = query.select(missionProof.image)
                .from(missionProof)
                .where(missionProof.missionId.eq(missionId),
                        missionProof.userId.eq(userId),
                        missionProof.date.eq(currentDate))
                .fetchFirst();
        if (verificationNumber == -1)
            return null;
        else return new GetMissionDataResponse(missionId, verificationNumber, description, missionImage);
    }

    //평가 하기
    @Override
    public boolean postMissionVerificate(int verificaterUserId, int verificatedUserId, int missionId) {
        Verification verification = new Verification();
        verification.setMissionId(missionId);
        verification.setUserId(verificatedUserId);
        verification.setVerifierId(verificaterUserId);
        em.persist(verification);
        return true;
}
}
