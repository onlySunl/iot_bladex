package org.springblade.common.page;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页查询参数基类。
 *
 * @author IOT服务端
 */
@Data
@NoArgsConstructor
@Schema(title = "PageParams", description = "分页参数")
public class PageParams<T> {

	/** 默认 ID 字段名 */
	public static final String ID_FIELD = "id";

	@NotNull(message = "查询对象model不能为空")
	@Schema(description = "查询参数", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid
	private T model;

	@Schema(description = "每页显示数据", example = "10")
	private long size = 10;

	@Schema(description = "当前页", example = "1")
	private long current = 1;

	@Schema(description = "排序,默认id", allowableValues = "id,createdTime,updatedTime", example = "id")
	private String sort = ID_FIELD;

	@Schema(description = "排序规则, 默认descending", allowableValues = "descending,ascending", example = "descending")
	private String order = "descending";

	@Schema(description = "扩展参数")
	private Map<String, Object> extra = MapUtil.newHashMap();

	public PageParams(long current, long size) {
		this.size = size;
		this.current = current;
	}

	/**
	 * 构建分页对象。
	 */
	@JsonIgnore
	public <E> IPage<E> buildPage() {
		return new Page<>(this.current, this.size);
	}

	/**
	 * 构建带排序的分页对象。
	 * 支持多字段排序。
	 *
	 * @param entityClazz 实体类（用于解析 @TableField/@TableId 注解获取数据库列名）
	 */
	@JsonIgnore
	public <E> IPage<E> buildPage(Class<?> entityClazz) {
		if (StrUtil.isEmpty(this.sort)) {
			return new Page<>(this.current, this.size);
		}

		Page<E> page = new Page<>(this.current, this.size);
		List<OrderItem> orders = new ArrayList<>();
		String[] sortArr = StrUtil.splitToArray(this.sort, StrPool.COMMA);
		String[] orderArr = StrUtil.splitToArray(this.order, StrPool.COMMA);
		int len = Math.min(sortArr.length, orderArr.length);

		for (int i = 0; i < len; i++) {
			String humpSort = sortArr[i];
			String underlineSort = getDbField(humpSort, entityClazz);
			orders.add(StrUtil.equalsAny(orderArr[i], "ascending", "ascend", "asc")
				? OrderItem.asc(underlineSort) : OrderItem.desc(underlineSort));
		}
		page.setOrders(orders);
		return page;
	}

	/** 计算当前分页偏移量 */
	@JsonIgnore
	public long offset() {
		if (this.current <= 1L) {
			return 0L;
		}
		return (this.current - 1) * this.size;
	}

	@JsonIgnore
	public PageParams<T> put(String key, Object value) {
		if (this.extra == null) {
			this.extra = new HashMap<>(16);
		}
		this.extra.put(key, value);
		return this;
	}

	@JsonIgnore
	public PageParams<T> putAll(Map<String, Object> extra) {
		if (this.extra == null) {
			this.extra = new HashMap<>(16);
		}
		this.extra.putAll(extra);
		return this;
	}

	// ── 驼峰字段名 → 数据库列名 ──

	/**
	 * 将驼峰属性名转换为数据库字段名。
	 * 优先取 @TableField 或 @TableId 注解的 value，无注解则驼峰转下划线。
	 */
	private static String getDbField(String humpField, Class<?> entityClazz) {
		if (entityClazz == null) {
			return StrUtil.toUnderlineCase(humpField);
		}
		Field field = ReflectUtil.getField(entityClazz, humpField);
		if (field == null) {
			return StrUtil.toUnderlineCase(humpField);
		}
		// @TableField
		TableField tableField = field.getAnnotation(TableField.class);
		if (tableField != null && StringUtils.isNotBlank(tableField.value())) {
			return tableField.value();
		}
		// @TableId
		TableId tableId = field.getAnnotation(TableId.class);
		if (tableId != null && StringUtils.isNotBlank(tableId.value())) {
			return tableId.value();
		}
		return StrUtil.toUnderlineCase(humpField);
	}
}
