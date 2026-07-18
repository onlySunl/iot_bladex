package org.springblade.modules.iot.mybatis.core.util;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.modules.iot.common.entity.PageParam;
import org.springblade.modules.iot.common.entity.SortingField;

public class MyBatisUtils {

    public static <T> Page<T> buildPage(PageParam pageParam) {
        return buildPage(pageParam, null);
    }

    public static <T> Page<T> buildPage(PageParam pageParam, Collection<SortingField> sortingFields) {
        Page<T> page = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        if (CollectionUtil.isNotEmpty(sortingFields)) {
            for (SortingField field : sortingFields) {
                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(field.getField());
                orderItem.setAsc(field.isAsc());
                page.addOrder(orderItem);
            }
        }
        return page;
    }
}
