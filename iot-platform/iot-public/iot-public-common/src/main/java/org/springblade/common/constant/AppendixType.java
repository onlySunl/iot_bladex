package org.springblade.common.constant;

import java.util.ArrayList;
import java.util.List;

import org.springblade.common.utils.ArgumentAssert;

/**
 * 附件表中事先约定的业务类型。
 * <p>
 * 预定方式为：  定义一个系统级别的接口   此接口内包含这个系统内的所有表中需要的附件类型
 * key定时方式为entity_field
 * value定义方式为： 系统简称-entity-field
 *
 * @author mqttsnet
 * @date 2018/12/11
 */
public final class AppendixType {

    /**
     * 后端代码不需要使用该 业务类型时， 无需使用常量，直接在ALL_TYPES数组中写字符串即可。
     * 命名规则:
     * 业务表名_字段名
     */
    public static final List<String> ALL_TYPES = new ArrayList<>();


    private AppendixType() {
    }

    public static void assertType(String type) {
        ArgumentAssert.contain(ALL_TYPES, type, "附件类型未定义");
    }

    public interface System {
        /**
         * 默认库 应用logo
         */
        String DEF__APPLICATION__LOGO = "DEF__APPLICATION__LOGO";
        /**
         * 租户logo
         */
        String DEF__TENANT__LOGO = "DEF__TENANT__LOGO";

        /**
         * 默认库 用户头像
         *
         * @author mqttsnet
         * @date 2021/8/14 8:47 下午
         * @create [2021/8/14 8:47 下午 ] [mqttsnet] [初始创建]
         */
        String DEF__USER__AVATAR = "DEF__USER__AVATAR";
    }

    /**
     * 权限管理系统中有关的的附件类型定义
     *
     * @author mqttsnet
     */

    public interface Base {


    }

    /**
     * 文件 业务类型定义
     */
    interface File {
        /**
         * 基础库 文件中心
         *
         * @author mqttsnet
         * @date 2021/8/14 8:45 下午
         * @create [2021/8/14 8:45 下午 ] [mqttsnet] [初始创建]
         */
        String BASE__FILE = "BASE__FILE";
    }


    /**
     * 消息系统 业务类型定义
     */
    interface Msg {
        /**
         * 扩展库 消息内容附件
         *
         * @author mqttsnet
         * @date 2021/8/14 8:47 下午
         * @create [2021/8/14 8:47 下午 ] [mqttsnet] [初始创建]
         */
        String EXTEND__MSG__CONTENT = "EXTEND__MSG__CONTENT";
    }

    /**
     * 物联网-Link 设备集成服务 业务类型定义
     */
    public interface Link {
        /**
         * 基础(租户)库 CA证书 附件
         *
         * @author mqttsnet
         * @date 2025/6/14 6:47 下午
         * @create [2025/6/14 6:47 下午 ] [mqttsnet] [初始创建]
         */
        String BASE__CA__CERT__CONTENT = "BASE__CA__CERT__CONTENT";
    }


}
