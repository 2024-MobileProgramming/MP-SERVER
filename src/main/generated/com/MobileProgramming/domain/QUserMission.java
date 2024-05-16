package com.MobileProgramming.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserMission is a Querydsl query type for UserMission
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserMission extends EntityPathBase<UserMission> {

    private static final long serialVersionUID = -1537585701L;

    public static final QUserMission userMission = new QUserMission("userMission");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Integer> missionId = createNumber("missionId", Integer.class);

    public final DatePath<java.sql.Date> updateDate = createDate("updateDate", java.sql.Date.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QUserMission(String variable) {
        super(UserMission.class, forVariable(variable));
    }

    public QUserMission(Path<? extends UserMission> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserMission(PathMetadata metadata) {
        super(UserMission.class, metadata);
    }

}

