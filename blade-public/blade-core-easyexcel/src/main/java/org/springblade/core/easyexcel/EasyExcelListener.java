package org.springblade.core.easyexcel;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.event.AnalysisEventListener;
import cn.idev.excel.exception.ExcelAnalysisException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springblade.basic.utils.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * -----------------------------------------------------------------------------
 * File Name: EasyExcelListener
 * -----------------------------------------------------------------------------
 * Description: EasyExcelListener is used for handling Excel import events and validation.
 * <p>
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/6/19       xiaonannet        1.0        Initial creation
 * 2024/6/20       xiaonannet        1.1        Added support for error export
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/6/19 19:07
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class EasyExcelListener<T> extends AnalysisEventListener<T> {
    private static final int BATCH_COUNT = 5000;
    @Getter
    private final List<T> successList = new ArrayList<>();
    @Getter
    private final List<ExcelImportErrDto<T>> errList = new ArrayList<>();
    private final ExcelCheckManager<T> excelCheckManager;
    private final List<T> list = new ArrayList<>();
    private final Class<T> clazz;
    private final boolean isErrorExport;
    private final HttpServletResponse response;

    public EasyExcelListener(HttpServletResponse response, ExcelCheckManager<T> excelCheckManager, Class<T> clazz, boolean isErrorExport) {
        this.excelCheckManager = excelCheckManager;
        this.clazz = clazz;
        this.isErrorExport = isErrorExport;
        this.response = response;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        Map<Integer, String> resultMap = null;
        resultMap = EasyExcelValiHelper.validateEntity(t);
        if (resultMap != null) {
            ExcelImportErrDto<T> excelImportErrObjectDto = new ExcelImportErrDto<>(t, resultMap);
            errList.add(excelImportErrObjectDto);
        } else {
            list.add(t);
        }
        if (list.size() >= BATCH_COUNT) {
            processBatch();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (!list.isEmpty()) {
            processBatch();
        }
        if (isErrorExport && !errList.isEmpty()) {
            try {
                exportErrorExcel();
            } catch (IOException e) {
                log.error("导出错误excel失败", e);
            }
        }
    }

    private void processBatch() {
        if (excelCheckManager != null) {
            ExcelCheckResult<T> result = excelCheckManager.checkImportExcel(list);
            successList.addAll(Optional.ofNullable(result.getSuccessDtos()).orElse(Collections.emptyList()));
            errList.addAll(Optional.ofNullable(result.getErrDtos()).orElse(Collections.emptyList()));
        }
        list.clear();
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        if (clazz != null) {
            try {
                Map<Integer, String> indexNameMap = getIndexNameMap(clazz);
                for (Map.Entry<Integer, String> entry : indexNameMap.entrySet()) {
                    Integer key = entry.getKey();
                    String expectedValue = entry.getValue();
                    String actualValue = headMap.get(key);
                    if (StringUtils.isEmpty(actualValue) || !actualValue.equals(expectedValue)) {
                        throw new ExcelAnalysisException("解析excel出错，请传入正确格式的excel");
                    }
                }
            } catch (NoSuchFieldException e) {
                log.error("解析excel出错", e);
            }
        }
    }

    public Map<Integer, String> getIndexNameMap(Class<T> clazz) throws NoSuchFieldException {
        Map<Integer, String> result = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            if (excelProperty != null) {
                int index = excelProperty.index();
                index = index == -1 ? i : index;
                String value = String.join("", excelProperty.value());
                result.put(index, value);
            }
        }
        return result;
    }

    private void exportErrorExcel() throws IOException {
        // 将错误对象列表中的对象映射到相应的类型，并收集到列表中
        List<T> userResultList = errList.stream()
                .map(ExcelImportErrDto::getObject)
                .map(clazz::cast)
                .collect(Collectors.toList());

        // 将错误对象列表中的错误信息映射到相应的类型，并收集到列表中
        List<Map<Integer, String>> errMsgList = errList.stream()
                .map(ExcelImportErrDto::getCellMap)
                .collect(Collectors.toList());

        // 如果结果列表不为空，则写入 Excel 文件
        if (!userResultList.isEmpty()) {
            EasyExcelUtils.webWriteExcel(response, userResultList, clazz, errMsgList, "导入错误信息");
        }
    }
}


