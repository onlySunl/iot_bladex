
/*
 *
 *  * | Licensed 未经许可不能去掉「Enjoy-iot」相关版权
 *  * +----------------------------------------------------------------------
 *  * | Author: xw2sy@163.com | Tel: 19918996474
 *  * +----------------------------------------------------------------------
 *
 *  Copyright [2025] [Enjoy-iot] | Tel: 19918996474
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * /
 */
package org.springblade.modules.iot.entity;

import com.tangzc.mybatisflex.autotable.annotations.AutoColumn;

import com.baomidou.mybatisplus.annotation.TableField;

import org.springblade.common.entity.CustomBaseEntity;

// import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 告警记录 DO
 *
 * @author EnjoyIot
 */
@TableName("eiot_alert_record")
// @KeySequence("eiot_alert_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlertRecordDO extends CustomBaseEntity {

    /**
     * 告警时间
     */
    @AutoColumn(comment = "告警时间")
    @TableField("alert_time")
    private Long alertTime;
    /**
     * 告警详情
     */
    @AutoColumn(comment = "告警详情")
    @TableField("details")
    private String details;
    /**
     * 告警等级
     */
    @AutoColumn(comment = "告警等级")
    @TableField("level")
    private String level;
    /**
     * 告警名称
     */
    @AutoColumn(comment = "告警名称")
    @TableField("name")
    private String name;
    /**
     * 是否已读
     */
    @AutoColumn(comment = "是否已读")
    @TableField("read_flg")
    private Boolean readFlg;
    /**
     * 机构id
     */
    @AutoColumn(comment = "机构id")
    @TableField("dept_id")
    private Long deptId;

}
