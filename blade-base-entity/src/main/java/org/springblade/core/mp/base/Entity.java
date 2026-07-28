package org.springblade.core.mp.base;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体接口
 *
 * @author Chill
 */
public interface Entity extends Serializable {

    /**
     * 获取ID
     */
    Long getId();

    /**
     * 设置ID
     */
    void setId(Long id);

    /**
     * 获取创建时间
     */
    LocalDateTime getCreateTime();

    /**
     * 设置创建时间
     */
    void setCreateTime(LocalDateTime createTime);

    /**
     * 获取更新时间
     */
    LocalDateTime getUpdateTime();

    /**
     * 设置更新时间
     */
    void setUpdateTime(LocalDateTime updateTime);

    /**
     * 获取创建人
     */
    Long getCreateUser();

    /**
     * 设置创建人
     */
    void setCreateUser(Long createUser);

    /**
     * 获取更新人
     */
    Long getUpdateUser();

    /**
     * 设置更新人
     */
    void setUpdateUser(Long updateUser);

    /**
     * 获取删除状态
     */
    Integer getIsDeleted();

    /**
     * 设置删除状态
     */
    void setIsDeleted(Integer isDeleted);

    /**
     * 获取租户ID
     */
    String getTenantId();

    /**
     * 设置租户ID
     */
    void setTenantId(String tenantId);
}
