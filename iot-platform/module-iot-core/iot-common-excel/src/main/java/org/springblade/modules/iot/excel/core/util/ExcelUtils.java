package org.springblade.modules.iot.excel.core.util;

import cn.idev.excel.EasyExcel;
import cn.idev.excel.converters.longconverter.LongStringConverter;
import cn.idev.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.springblade.modules.iot.excel.core.handler.SelectSheetWriteHandler;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Excel 工具类
 */
public class ExcelUtils {

    /**
     * 将列表以 Excel 响应给前端
     */
    public static <T> void write(HttpServletResponse response, String filename, String sheetName,
                                 Class<T> head, List<T> data) throws IOException {
        EasyExcel.write(response.getOutputStream(), head)
                .autoCloseStream(false)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .registerWriteHandler(new SelectSheetWriteHandler(head))
                .registerConverter(new LongStringConverter())
                .sheet(sheetName).doWrite(data);
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8.name()));
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
    }

    public static <T> List<T> read(MultipartFile file, Class<T> head) throws IOException {
        return EasyExcel.read(file.getInputStream(), head, null)
                .autoCloseStream(false)
                .doReadAllSync();
    }
}
