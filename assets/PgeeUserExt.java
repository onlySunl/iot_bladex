package org.springblade.kaoyan.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tangzc.autotable.annotation.AutoColumn;
import com.tangzc.autotable.annotation.enums.DefaultValueEnum;
import com.tangzc.autotable.annotation.mysql.MysqlCharset;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tenant.mp.TenantEntity;

import java.math.BigDecimal;
import java.util.Date;
@Data
@TableName("pgee_user_ext")
@EqualsAndHashCode(callSuper = true)

@Table(value = "pgee_user_ext", comment = "用户扩展表")
public class PgeeUserExt extends BaseEntity {
	/**
	 * 注册人员ID
	 */
	@TableField(value = "user_id")
	private Long userId;
	/**
	 * 邀请码
	 */
	@AutoColumn(comment = "邀请码", length = 12, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	@TableField(value = "invite_code")
	private String inviteCode;

	/**
	 * 邀请码
	 */
	@AutoColumn(comment = "全码", length = 20, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	@TableField(value = "full_code")
	private String fullCode;
	/**
	 * 个人分享二维码
	 */
	@AutoColumn(comment = "个人分享二维码", length = 1000, defaultValueType = DefaultValueEnum.EMPTY_STRING)
	@TableField(value = "share_qr_code")
	private String shareQrCode;

	@AutoColumn(comment = "图片在附件表中的ID", length = 64, defaultValueType = DefaultValueEnum.NULL)
	@TableField(value = "qr_code_file_id")
	private Long qrCodeFileId;
	/**
	 * 积分
	 */
	@AutoColumn(comment = "积分", length = 5, defaultValueType = DefaultValueEnum.NULL)
	@TableField(value = "app_integral")
	private Integer appIntegral;

	/**
	 * 是否择校VIP
	 */
	@AutoColumn(comment = "是否择校VIP", length = 1, defaultValueType = DefaultValueEnum.NULL)
	@TableField(value = "is_choose_vip")
	private Boolean isChooseVip;

	@AutoColumn(comment = "缴费日期",  defaultValueType = DefaultValueEnum.NULL)
	@TableField(value = "choose_start_date")
	@JsonFormat(
			pattern = "yyyy-MM-dd"
	)
	private Date chooseStartDate;


	/**
	 * 择校VIP到期日期
	 */
	@AutoColumn(comment = "择校VIP到期日期",  defaultValueType = DefaultValueEnum.NULL)
	@TableField(value = "choose_vip_date")
	@JsonFormat(
			pattern = "yyyy-MM-dd"
	)
	private Date chooseVipDate;

	/**
	 * 是否调剂VIP
	 */
	@AutoColumn(comment = "是否调剂VIP", length = 1, defaultValueType = DefaultValueEnum.NULL)
	@TableField(value = "is_adjust_vip")
	private Boolean isAdjustVip;

	/**
	 * 调剂VIP开通日期
	 */
	@AutoColumn(comment = "调剂VIP开通日期",  defaultValueType = DefaultValueEnum.NULL)
	@TableField(value = "adjust_start_date")
	@JsonFormat(
			pattern = "yyyy-MM-dd"
	)
	private Date adjustStartDate;

	/**
	 * 调剂VIP到期日期
	 */
	@AutoColumn(comment = "调剂VIP到期日期",  defaultValueType = DefaultValueEnum.NULL)
	@TableField(value = "adjust_vip_date")
	@JsonFormat(
			pattern = "yyyy-MM-dd"
	)
	private Date adjustVipDate;

	/**
	 * 是否AI择校
	 */
	@AutoColumn(comment = "是否AI择校", length = 1, defaultValueType = DefaultValueEnum.NULL)
	@TableField(value = "is_choose_ai")
	private Boolean isChooseAi;

	/**
	 * AI择校到期日期
	 */
	@AutoColumn(comment = "AI择校到期日期",  defaultValueType = DefaultValueEnum.NULL)
	@TableField(value = "choose_ai_date")
	private Date chooseAiDate;

	/**
	 * 是否AI调剂
	 */
	@AutoColumn(comment = "是否AI调剂", length = 1, defaultValueType = DefaultValueEnum.NULL)
	@TableField(value = "is_adjust_ai")
	private Boolean isAdjustAi;

	/**
	 * AI调剂到期日期
	 */
	@AutoColumn(comment = "AI调剂到期日期",  defaultValueType = DefaultValueEnum.NULL)
	@TableField(value = "adjust_ai_date")
	private Date adjustAiDate;

	/**
	 * 是否会员
	 */
	@AutoColumn(comment = "是否会员", length = 1, defaultValueType = DefaultValueEnum.NULL)
	@TableField(value = "is_vip")
	private Boolean isVip;

	/**
	 * 是否会员
	 */
	@AutoColumn(comment = "专业修改次数", length = 1, defaultValueType = DefaultValueEnum.NULL)
	@TableField(value = "modify_speciality_size")
	private Integer modifySpecialitySize;

	/**
	 * 提成
	 */
	@AutoColumn(comment = "提成", length = 12, decimalLength = 6)
	@TableField(value = "commission")
	private BigDecimal commission;

	/**
	 * 是否会员
	 */
	@AutoColumn(comment = "用户类型[1：部门管理者,2:推广员,8:普通用户]", length = 1, defaultValueType = DefaultValueEnum.NULL)
	@TableField(value = "user_type")
	private Integer userType;
}
