package org.springblade.modules.iot.persistence.page;

import org.springblade.modules.iot.common.utils.StringUtils;
import java.util.List;

/**
 * 分页工具类 - 已迁移至 MyBatis-Plus IPage
 * 保留此类以兼容旧代码，startPage() 为 no-op
 */
public class PageUtils {

    /**
     * 设置请求分页数据 (no-op, 使用 MyBatis-Plus IPage 替代)
     */
    public static void startPage() {
        // No-op: 使用 MyBatis-Plus 的 IPage 进行分页
    }

    /**
     * 封装分页数据
     */
    public static <T> TableDataInfo<T> getDataTable(List<T> list) {
        TableDataInfo<T> rspData = new TableDataInfo<>();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setMsg("查询成功");
        rspData.setTotal(list.size());
        return rspData;
    }
}
