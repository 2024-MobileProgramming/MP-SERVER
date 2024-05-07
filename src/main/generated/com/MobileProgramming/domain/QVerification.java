package com.MobileProgramming.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVerification is a Querydsl query type for Verification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVerification extends EntityPathBase<Verification> {

    private static final long serialVersionUID = 69811905L;

    public static final QVerification verification = new QVerification("verification");

    public final NumberPath<Integer> missionId = createNumber("missionId", Integer.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public final NumberPath<Integer> verifierId = createNumber("verifierId", Integer.class);

    public final DatePath<java.sql.Date> verifyDate = createDate("verifyDate", java.sql.Date.class);

    public QVerification(String variable) {
        super(Verification.class, forVariable(variable));
    }

    public QVerification(Path<? extends Verification> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVerification(PathMetadata metadata) {
        super(Verification.class, metadata);
    }

}

