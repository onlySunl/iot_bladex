package org.springblade.modules.iot.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * 分页工具类 - BladeX/MyBatis-Plus 分页转换
 */
public class PageUtil {

    /**
     * 创建分页对象
     * @param page 目标分页对象
     * @param records 数据列表
     * @param total 总记录数
     * @return 分页对象
     */
    public static <T> IPage<T> initPage(IPage<T> page, List<T> records, long total) {
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }
}
