package org.springblade.modules.iot.common.enums;


import lombok.Getter;

/**
 * 服务在线状态枚举类
 * 适配海康ISUP/物联网设备服务状态场景
 *
 * @author Blade
 * @date 2024-XX-XX
 */
@Getter
public enum ServiceStatus {

    /**
     * 在线（服务正常运行）
     */
    ONLINE(1, "在线", "服务正常连接并运行"),

    /**
     * 离线（服务断开连接）
     */
    OFFLINE(0, "离线", "服务连接断开或未响应"),

    /**
     * 异常（服务运行异常，非完全离线）
     */
    ABNORMAL(2, "异常", "服务在线但功能异常"),

    /**
     * 维护中（人工维护状态）
     */
    MAINTENANCE(3, "维护中", "服务处于人工维护阶段");

    /**
     * 状态编码（数据库存储/接口传输用）
     */
    private final Integer code;

    /**
     * 状态名称（前端展示/日志输出用）
     */
    private final String name;

    /**
     * 状态描述（详细说明）
     */
    private final String desc;

    /**
     * 构造方法
     *
     * @param code 状态编码
     * @param name 状态名称
     * @param desc 状态描述
     */
    ServiceStatus(Integer code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    /**
     * 根据编码获取枚举实例
     *
     * @param code 状态编码
     * @return 对应的枚举实例，无匹配返回null
     */
    public static ServiceStatus getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (ServiceStatus status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    /**
     * 判断是否为在线状态
     *
     * @return true=在线，false=非在线
     */
    public boolean isOnline() {
        return this == ONLINE;
    }

    /**
     * 判断是否为离线状态
     *
     * @return true=离线，false=非离线
     */
    public boolean isOffline() {
        return this == OFFLINE;
    }

    /**
     * 判断是否为异常状态
     *
     * @return true=异常，false=非异常
     */
    public boolean isAbnormal() {
        return this == ABNORMAL;
    }

    /**
     * 重写toString，便于日志输出
     *
     * @return 状态信息（编码+名称）
     */
    @Override
    public String toString() {
        return String.format("ServiceStatus{code=%d, name='%s'}", code, name);
    }
}

