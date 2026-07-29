package org.springblade.core.easyexcel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * EasyExcel 配置属性
 *
 * @author Chill
 */
@Data
@ConfigurationProperties(prefix = "blade.easyexcel")
public class EasyExcelProperties {

    /**
     * 是否启用 EasyExcel
     */
    private boolean enabled = true;

    /**
     * 默认批次大小
     */
    private int defaultBatchSize = 100;

    /**
     * 默认工作表名
     */
    private String defaultSheetName = "Sheet1";

    /**
     * 是否自动创建表头
     */
    private boolean autoCreateHeader = true;

    /**
     * 日期格式化
     */
    private String dateFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * 数字格式化
     */
    private String numberFormat = "#.##";
}
