package org.springblade.modules.iot.excel.core.handler;

import cn.idev.excel.write.handler.SheetWriteHandler;
import cn.idev.excel.write.metadata.holder.WriteSheetHolder;
import cn.idev.excel.write.metadata.holder.WriteWorkbookHolder;
import org.springblade.modules.iot.excel.core.annotations.ExcelColumnSelect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 下拉选择 Sheet 写入处理器
 */
public class SelectSheetWriteHandler implements SheetWriteHandler {

    private final Class<?> head;

    public SelectSheetWriteHandler(Class<?> head) {
        this.head = head;
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        // 处理 ExcelColumnSelect 注解
        List<Field> selectFields = getSelectFields();
        if (selectFields.isEmpty()) {
            return;
        }
        // TODO: 实现下拉选择的数据验证
    }

    private List<Field> getSelectFields() {
        List<Field> result = new ArrayList<>();
        for (Field field : head.getDeclaredFields()) {
            if (field.isAnnotationPresent(ExcelColumnSelect.class)) {
                result.add(field);
            }
        }
        return result;
    }
}
