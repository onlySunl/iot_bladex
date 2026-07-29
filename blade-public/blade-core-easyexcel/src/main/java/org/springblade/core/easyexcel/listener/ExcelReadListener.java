package org.springblade.core.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Excel 读取监听器
 *
 * @author Chill
 */
@Slf4j
public class ExcelReadListener<T> implements ReadListener<T> {

    /**
     * 默认批次大小
     */
    private static final int DEFAULT_BATCH_SIZE = 100;

    /**
     * 批次大小
     */
    private final int batchSize;

    /**
     * 缓存的数据列表
     */
    private final List<T> cachedDataList;

    /**
     * 数据处理器
     */
    private final DataProcessor<T> dataProcessor;

    /**
     * 总数据量
     */
    private int totalCount = 0;

    public ExcelReadListener(DataProcessor<T> dataProcessor) {
        this(dataProcessor, DEFAULT_BATCH_SIZE);
    }

    public ExcelReadListener(DataProcessor<T> dataProcessor, int batchSize) {
        this.dataProcessor = dataProcessor;
        this.batchSize = batchSize;
        this.cachedDataList = new ArrayList<>(batchSize);
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        cachedDataList.add(data);
        totalCount++;
        if (cachedDataList.size() >= batchSize) {
            saveData();
            cachedDataList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (!cachedDataList.isEmpty()) {
            saveData();
            cachedDataList.clear();
        }
        log.info("Excel 读取完成，共 {} 条数据", totalCount);
    }

    /**
     * 保存数据
     */
    private void saveData() {
        log.debug("处理 {} 条数据", cachedDataList.size());
        if (dataProcessor != null) {
            dataProcessor.process(cachedDataList);
        }
    }

    /**
     * 获取总数据量
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 数据处理器接口
     */
    @FunctionalInterface
    public interface DataProcessor<T> {
        /**
         * 处理数据
         *
         * @param dataList 数据列表
         */
        void process(List<T> dataList);
    }
}
