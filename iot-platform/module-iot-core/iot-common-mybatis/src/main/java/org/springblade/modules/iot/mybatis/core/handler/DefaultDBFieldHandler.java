package org.springblade.modules.iot.mybatis.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

@Slf4j
public class DefaultDBFieldHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasGetter("createTime")) {
            Object createTime = getFieldValByName("createTime", metaObject);
            if (createTime == null) {
                setFieldValByName("createTime", LocalDateTime.now(), metaObject);
            }
        }
        if (metaObject.hasGetter("updateTime")) {
            Object updateTime = getFieldValByName("updateTime", metaObject);
            if (updateTime == null) {
                setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasGetter("updateTime")) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
