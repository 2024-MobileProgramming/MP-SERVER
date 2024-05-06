package com.MobileProgramming.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMissionProof is a Querydsl query type for MissionProof
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMissionProof extends EntityPathBase<MissionProof> {

    private static final long serialVersionUID = 1481693118L;

    public static final QMissionProof missionProof = new QMissionProof("missionProof");

    public final DatePath<java.sql.Date> date = createDate("date", java.sql.Date.class);

    public final StringPath description = createString("description");

    public final ArrayPath<byte[], Byte> image = createArray("image", byte[].class);

    public final NumberPath<Integer> missionId = createNumber("missionId", Integer.class);

    public final NumberPath<Integer> proofId = createNumber("proofId", Integer.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QMissionProof(String variable) {
        super(MissionProof.class, forVariable(variable));
    }

    public QMissionProof(Path<? extends MissionProof> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMissionProof(PathMetadata metadata) {
        super(MissionProof.class, metadata);
    }

}

