package org.springblade.core.easyexcel.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.easyexcel.listener.ExcelReadListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Excel 工具类
 * 基于阿里巴巴 EasyExcel 封装
 *
 * @author Chill
 */
@Slf4j
public class ExcelUtil {

    /**
     * 读取 Excel 文件
     *
     * @param inputStream 输入流
     * @param clazz       数据类
     * @param processor   数据处理器
     * @param <T>         数据类型
     */
    public static <T> void read(InputStream inputStream, Class<T> clazz, ExcelReadListener.DataProcessor<T> processor) {
        read(inputStream, clazz, processor, ExcelReadListener.DEFAULT_BATCH_SIZE);
    }

    /**
     * 读取 Excel 文件（指定批次大小）
     *
     * @param inputStream 输入流
     * @param clazz       数据类
     * @param processor   数据处理器
     * @param batchSize   批次大小
     * @param <T>         数据类型
     */
    public static <T> void read(InputStream inputStream, Class<T> clazz, ExcelReadListener.DataProcessor<T> processor, int batchSize) {
        ExcelReadListener<T> listener = new ExcelReadListener<>(processor, batchSize);
        EasyExcel.read(inputStream, clazz, listener).sheet().doRead();
    }

    /**
     * 读取 Excel 文件到列表
     *
     * @param inputStream 输入流
     * @param clazz       数据类
     * @param <T>         数据类型
     * @return 数据列表
     */
    public static <T> List<T> readToList(InputStream inputStream, Class<T> clazz) {
        return EasyExcel.read(inputStream).head(clazz).sheet().doReadSync();
    }

    /**
     * 写入 Excel 到输出流
     *
     * @param outputStream 输出流
     * @param clazz        数据类
     * @param dataList     数据列表
     * @param <T>          数据类型
     */
    public static <T> void write(OutputStream outputStream, Class<T> clazz, List<T> dataList) {
        write(outputStream, clazz, dataList, "Sheet1");
    }

    /**
     * 写入 Excel 到输出流（指定工作表名）
     *
     * @param outputStream 输出流
     * @param clazz        数据类
     * @param dataList     数据列表
     * @param sheetName    工作表名
     * @param <T>          数据类型
     */
    public static <T> void write(OutputStream outputStream, Class<T> clazz, List<T> dataList, String sheetName) {
        EasyExcel.write(outputStream, clazz).sheet(sheetName).doWrite(dataList);
    }

    /**
     * 导出 Excel 到 HTTP 响应
     *
     * @param response  HTTP 响应
     * @param fileName  文件名
     * @param sheetName 工作表名
     * @param clazz     数据类
     * @param dataList  数据列表
     * @param <T>       数据类型
     * @throws IOException IO 异常
     */
    public static <T> void export(HttpServletResponse response, String fileName, String sheetName,
                                   Class<T> clazz, List<T> dataList) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), clazz)
            .sheet(sheetName)
            .doWrite(dataList);
    }

    /**
     * 导出 Excel 到 HTTP 响应（使用默认工作表名）
     *
     * @param response HTTP 响应
     * @param fileName 文件名
     * @param clazz    数据类
     * @param dataList 数据列表
     * @param <T>      数据类型
     * @throws IOException IO 异常
     */
    public static <T> void export(HttpServletResponse response, String fileName,
                                   Class<T> clazz, List<T> dataList) throws IOException {
        export(response, fileName, "Sheet1", clazz, dataList);
    }

    /**
     * 多工作表写入
     *
     * @param outputStream 输出流
     * @param sheets       工作表数据
     */
    public static void writeMultiSheet(OutputStream outputStream, List<SheetData> sheets) {
        try (ExcelWriter excelWriter = EasyExcel.write(outputStream).build()) {
            for (int i = 0; i < sheets.size(); i++) {
                SheetData sheetData = sheets.get(i);
                WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetData.getSheetName())
                    .head(sheetData.getClazz())
                    .build();
                excelWriter.write(sheetData.getDataList(), writeSheet);
            }
        }
    }

    /**
     * 工作表数据
     */
    public static class SheetData {
        private String sheetName;
        private Class<?> clazz;
        private List<?> dataList;

        public SheetData(String sheetName, Class<?> clazz, List<?> dataList) {
            this.sheetName = sheetName;
            this.clazz = clazz;
            this.dataList = dataList;
        }

        public String getSheetName() {
            return sheetName;
        }

        public Class<?> getClazz() {
            return clazz;
        }

        public List<?> getDataList() {
            return dataList;
        }
    }
}
