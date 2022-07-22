package com.sinkiang.ddd.sample.infrastructure.repository.db;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sinkiang.ddd.sample.common.auth.AuthData;
import com.sinkiang.ddd.sample.common.auth.AuthDataInfoContext;
import com.sinkiang.ddd.sample.common.id.snowball.SnowBallGenerator;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author dengxj
 * @date 2022/7/20 17:03
 */
@Configuration
public class MybatisObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        strictInsertFill(metaObject, "id", String.class, SnowBallGenerator.nextId());
        LocalDateTime now = LocalDateTime.now();
        strictInsertFill(metaObject, "gmtCreate", LocalDateTime.class, now);
        strictInsertFill(metaObject, "gmtModify", LocalDateTime.class, now);

        strictInsertFill(metaObject, "creator", String.class, Optional.ofNullable(AuthDataInfoContext.getAuthData()).map(AuthData::getUsername).orElse("system"));
        strictInsertFill(metaObject, "modifier", String.class, Optional.ofNullable(AuthDataInfoContext.getAuthData()).map(AuthData::getUsername).orElse("system"));

        strictInsertFill(metaObject, "creatorName", String.class, Optional.ofNullable(AuthDataInfoContext.getAuthData()).map(AuthData::getPersonName).orElse("system"));
        strictInsertFill(metaObject, "modifierName", String.class, Optional.ofNullable(AuthDataInfoContext.getAuthData()).map(AuthData::getPersonName).orElse("system"));

        strictInsertFill(metaObject, "isValid", Integer.class, 1);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictUpdateFill(metaObject, "gmtModify", LocalDateTime.class, LocalDateTime.now());
        strictUpdateFill(metaObject, "modifier", String.class, Optional.ofNullable(AuthDataInfoContext.getAuthData()).map(AuthData::getUsername).orElse("system"));
        strictUpdateFill(metaObject, "modifierName", String.class, Optional.ofNullable(AuthDataInfoContext.getAuthData()).map(AuthData::getPersonName).orElse("system"));
    }
}


