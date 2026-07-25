package org.springblade.modules.iot.mybatis.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.utils.Func;

import java.util.Date;

/**
 * 通用数据库字段填充处理器
 *
 * <p>BladeX 4.9.0 的 BaseEntity 不再使用 @TableField(fill=...) 注解，
 * 改为通过 DataChangeRecorderInnerInterceptor 处理。但该拦截器依赖
 * 登录用户上下文，Feign 内部调用时无法获取用户信息。
 *
 * <p>此 Handler 通过 setFieldValByName 直接设置字段值（不依赖 @TableField 注解），
 * 确保所有场景下字段都能正确赋值。
 *
 * @author EnjoyIot
 */
@Slf4j
public class DefaultDBFieldHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        setFieldValByNameIfAbsent("createTime", now, metaObject);
        setFieldValByNameIfAbsent("updateTime", now, metaObject);

        // 尝试获取当前登录用户
        try {
            BladeUser user = SecureUtil.getUser();
            if (user != null) {
                setFieldValByNameIfAbsent("createUser", user.getUserId(), metaObject);
                setFieldValByNameIfAbsent("createDept", Func.firstLong(user.getDeptId()), metaObject);
                setFieldValByNameIfAbsent("updateUser", user.getUserId(), metaObject);
            }
        } catch (Exception e) {
            log.debug("无法获取当前用户信息，跳过 createUser/createDept 填充");
        }

        // status 默认值
        setFieldValByNameIfAbsent("status", 1, metaObject);
        setFieldValByNameIfAbsent("isDeleted", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime", new Date(), metaObject);

        // 尝试获取当前登录用户
        try {
            BladeUser user = SecureUtil.getUser();
            if (user != null) {
                setFieldValByName("updateUser", user.getUserId(), metaObject);
            }
        } catch (Exception e) {
            log.debug("无法获取当前用户信息，跳过 updateUser 填充");
        }
    }

    /**
     * 仅当字段值为 null 时才设置
     */
    private void setFieldValByNameIfAbsent(String fieldName, Object value, MetaObject metaObject) {
        if (metaObject.hasGetter(fieldName)) {
            Object currentVal = getFieldValByName(fieldName, metaObject);
            if (currentVal == null) {
                setFieldValByName(fieldName, value, metaObject);
            }
        }
    }
}
