package org.springblade.modules.iot.productversionchangelog.entity;
import org.springblade.basic.base.entity.Entity;
import com.tangzc.autotable.annotation.AutoTable;
import com.tangzc.autotable.annotation.AutoColumn;

import java.io.Serial;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 产品物模型版本变更日志实体 ── append-only 审计流水,产品树在未发布前每次有效变更就追加一行(草稿编辑流水)。
 * 区别于版本对比({@code ProductVersionService.diff} 的两版本全量差异);发布动作不写本表(走 product_publish_record)。
 *
 * @author mqttsnet
 * @see com.mqttsnet.thinglinks.productversionchangelog.enumeration.ProductVersionChangeTypeEnum
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@AutoTable(value = "iot_product_version_change_log", comment = "ProductVersionChangeLog table")
public class ProductVersionChangeLog extends Entity<Long> {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 产品标识。
     */
    @AutoColumn(value = "product_identification", comment = "产品标识。")
    private String productIdentification;

    /**
     * 版本序号:本批变更归属版本(对应 {@code product_version.version_no})。
     */
    @AutoColumn(value = "version_no", comment = "版本序号:本批变更归属版本(对应 {@code product_version.version_no})。")
    private String versionNo;

    /**
     * 变更类型(0-新增,1-编辑,2-删除)。
     */
    @AutoColumn(value = "change_type", comment = "变更类型(0-新增,1-编辑,2-删除)。")
    private Integer changeType;

    /**
     * 变更维度(0-产品信息 1-服务 2-属性 3-命令)。
     */
    @AutoColumn(value = "target_type", comment = "变更维度(0-产品信息 1-服务 2-属性 3-命令)。")
    private Integer targetType;

    /**
     * 变更摘要(人类可读,如"新增 1 个服务、修改 2 个属性")。
     */
    @AutoColumn(value = "change_summary", comment = "变更摘要(人类可读,如\"新增 1 个服务、修改 2 个属性\")。")
    private String changeSummary;

    /**
     * 字段级变更明细 JSON(对应 {@code ProductVersionDiffVO} 序列化结果,覆盖产品所有字段)。
     */
    @AutoColumn(value = "change_detail_json", comment = "字段级变更明细 JSON(对应 {@code ProductVersionDiffVO} 序列化结果,覆盖产品所有字段)。")
    private String changeDetailJson;

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
