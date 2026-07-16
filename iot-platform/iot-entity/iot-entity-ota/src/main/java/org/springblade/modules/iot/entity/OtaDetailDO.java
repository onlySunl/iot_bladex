
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
 * 设备ota详情 DO
 *
 * @author EnjoyIot
 */
@TableName("device_ota_detail")
// @KeySequence("device_ota_detail_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtaDetailDO extends CustomBaseEntity {

    /**
     * 设备id
     */
    @AutoColumn
    @TableField("device_id")
    private Long deviceId;
    /**
     * 设备名称
     */
    @AutoColumn
    @TableField("device_name")
    private String deviceName;
    /**
     * 模块
     */
    @AutoColumn
    @TableField("module")
    private String module;
    /**
     * ota信息id
     */
    @AutoColumn
    @TableField("ota_info_id")
    private Long otaInfoId;
    /**
     * 产品key
     */
    @AutoColumn
    @TableField("product_key")
    private String productKey;
    /**
     * 步骤
     */
    @AutoColumn
    @TableField("step")
    private Integer step;
    /**
     * 任务id
     */
    @AutoColumn
    @TableField("task_id")
    private Long taskId;
    /**
     * 版本
     */
    @AutoColumn
    @TableField("version")
    private String version;
    /**
     * 机构id
     */
    @AutoColumn
    @TableField("dept_id")
    private Long deptId;

}
