package org.springblade.modules.iot.productversion.entity;
import org.springblade.basic.base.entity.Entity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import java.io.Serial;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 产品物模型版本快照实体,每次发布产生一行,product_snapshot_json 冻结当时整棵产品树,不可变。
 *
 * @author mqttsnet
 * @see com.mqttsnet.thinglinks.productversion.enumeration.ProductVersionStatusEnum
 * @see com.mqttsnet.thinglinks.productversion.enumeration.ProductPublishStrategyEnum
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "iot_product_version", comment = "ProductVersion table")
public class ProductVersion extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 产品标识。
     */
    @AutoColumn(value = "product_identification", comment = "产品标识。")
    private String productIdentification;

    /** 版本序号(系统发布时生成的不可变快照标识,16 位短雪花字符串)。 */
    @AutoColumn(value = "version_no", comment = "版本序号(系统发布时生成的不可变快照标识,16 位短雪花字符串)。")
    private String versionNo;

    /**
     * 版本状态(0-草稿,1-已发布,2-灰度中,3-影子,4-已回滚,5-已归档)。
     */
    @AutoColumn(value = "version_status", comment = "版本状态(0-草稿,1-已发布,2-灰度中,3-影子,4-已回滚,5-已归档)。")
    private Integer versionStatus;

    /**
     * 产品快照 JSON,对应 {@link com.mqttsnet.thinglinks.productversion.vo.snapshot.ProductSnapshotVO}。
     */
    @AutoColumn(value = "product_snapshot_json", comment = "产品快照 JSON,对应 {@link com.mqttsnet.thinglinks.productversion.vo.snapshot.ProductSnapshotVO}。")
    private String productSnapshotJson;

    /**
     * 发布策略(0-全量,1-灰度,2-影子)。
     */
    @AutoColumn(value = "publish_strategy", comment = "发布策略(0-全量,1-灰度,2-影子)。")
    private Integer publishStrategy;

    /**
     * 灰度配置 JSON。
     */
    @AutoColumn(value = "canary_config_json", comment = "灰度配置 JSON。")
    private String canaryConfigJson;

    /**
     * 发布时间。
     */
    @AutoColumn(value = "publish_time", comment = "发布时间。")
    private LocalDateTime publishTime;

    /**
     * 备注。
     */

    /**
     * 创建人组织。
     */
    @AutoColumn(value = "created_org_id", comment = "创建人组织。")
    private Long createdOrgId;

    /**
     * 逻辑删除标识(0-未删除、1-已删除)。
     */
    @TableLogic
    @AutoColumn(value = "deleted", comment = "逻辑删除标识(0-未删除、1-已删除)。")
    private Integer deleted;
}
