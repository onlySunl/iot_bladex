package org.springblade.core.easyexcel;

import cn.idev.excel.FastExcel;
import cn.idev.excel.write.builder.ExcelWriterBuilder;
import cn.idev.excel.write.metadata.style.WriteCellStyle;
import cn.idev.excel.write.style.HorizontalCellStyleStrategy;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * -----------------------------------------------------------------------------
 * File Name: EasyExcelUtils
 * -----------------------------------------------------------------------------
 * Description: Utility class for handling Excel import and export using EasyExcel library
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
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/6/19 19:04
 */
@Slf4j
public class EasyExcelUtils {
    /**
     * Exports data to an Excel file.
     *
     * @param response the HTTP response
     * @param objects  the data to be exported
     * @param clazz    the type of the data
     * @param fileName the name of the Excel file
     * @throws IOException if an I/O error occurs
     */
    public static void webWriteExcel(HttpServletResponse response, List<?> objects, Class<?> clazz, String fileName) throws IOException {
        webWriteExcel(response, objects, clazz, null, fileName);
    }

    /**
     * Exports data to an Excel file with error messages.
     *
     * @param response   the HTTP response
     * @param objects    the data to be exported
     * @param clazz      the type of the data
     * @param errMsgList the list of error messages
     * @param fileName   the name of the Excel file
     * @throws IOException if an I/O error occurs
     */
    public static void webWriteExcel(HttpServletResponse response, List<?> objects, Class<?> clazz, List<Map<Integer, String>> errMsgList, String fileName) throws IOException {
        webWriteExcel(response, objects, clazz, errMsgList, fileName, fileName);
    }

    /**
     * Exports data to an Excel file with a specific sheet name and error messages.
     *
     * @param response   the HTTP response
     * @param objects    the data to be exported
     * @param clazz      the type of the data
     * @param errMsgList the list of error messages
     * @param fileName   the name of the Excel file
     * @param sheetName  the name of the sheet
     * @throws IOException if an I/O error occurs
     */
    public static void webWriteExcel(HttpServletResponse response, List<?> objects, Class<?> clazz, List<Map<Integer, String>> errMsgList, String fileName, String sheetName) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", StandardCharsets.UTF_8));

        // 标题样式
        WriteCellStyle headCellStyle = new WriteCellStyle();
        headCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());

        // 数据样式
        WriteCellStyle contentCellStyle = new WriteCellStyle();
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headCellStyle, contentCellStyle);

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            ExcelWriterBuilder write = FastExcel.write(outputStream, clazz);
            if (!CollectionUtils.isEmpty(errMsgList)) {
                // inMemory(Boolean.TRUE)开启批注 批注在ErrorSheetWriteHandler中实现
                write.inMemory(Boolean.TRUE).registerWriteHandler(new ErrorSheetWriteHandler(errMsgList));
            }
            write.registerWriteHandler(horizontalCellStyleStrategy).sheet(sheetName).doWrite(objects);
        } catch (Exception e) {
            log.error("Failed to export Excel file", e);
            throw e;
        }
    }

    /**
     * Imports data from an Excel file.
     *
     * @param response        the HTTP response
     * @param fileInputStream the input stream of the Excel file
     * @param clazz           the type of the data
     * @param <T>             the type parameter
     * @return the listener for the import process
     * @throws Exception if an error occurs during the import
     */
    public static <T> EasyExcelListener<T> webImportExcel(HttpServletResponse response, InputStream fileInputStream, Class<T> clazz) throws Exception {
        return webImportExcel(response, fileInputStream, null, clazz, true);
    }

    /**
     * Imports data from an Excel file with an option to export error data.
     *
     * @param response        the HTTP response
     * @param fileInputStream the input stream of the Excel file
     * @param clazz           the type of the data
     * @param isErrorExport   whether to export error data
     * @param <T>             the type parameter
     * @return the listener for the import process
     * @throws Exception if an error occurs during the import
     */
    public static <T> EasyExcelListener<T> webImportExcel(HttpServletResponse response, InputStream fileInputStream, Class<T> clazz, boolean isErrorExport) throws Exception {
        return webImportExcel(response, fileInputStream, null, clazz, isErrorExport);
    }

    /**
     * Imports data from an Excel file with custom validation.
     *
     * @param response           the HTTP response
     * @param fileInputStream    the input stream of the Excel file
     * @param customCheckService the custom validation service
     * @param clazz              the type of the data
     * @param <T>                the type parameter
     * @return the listener for the import process
     * @throws Exception if an error occurs during the import
     */
    public static <T> EasyExcelListener<T> webImportExcel(HttpServletResponse response, InputStream fileInputStream, ExcelCheckManager<T> customCheckService, Class<T> clazz) throws Exception {
        return webImportExcel(response, fileInputStream, customCheckService, clazz, true);
    }

    /**
     * Imports data from an Excel file with custom validation and an option to export error data.
     *
     * @param response           the HTTP response
     * @param fileInputStream    the input stream of the Excel file
     * @param customCheckService the custom validation service
     * @param clazz              the type of the data
     * @param isErrorExport      whether to export error data
     * @param <T>                the type parameter
     * @return the listener for the import process
     * @throws Exception if an error occurs during the import
     */
    public static <T> EasyExcelListener<T> webImportExcel(HttpServletResponse response, InputStream fileInputStream, ExcelCheckManager<T> customCheckService, Class<T> clazz, boolean isErrorExport) throws Exception {
        EasyExcelListener<T> easyExcelListener = new EasyExcelListener<>(response, customCheckService, clazz, isErrorExport);
        FastExcel.read(fileInputStream, clazz, easyExcelListener).sheet().doRead();
        return easyExcelListener;
    }

    /**
     * Writes a response with a specific message and content type.
     *
     * @param response    the HTTP response
     * @param message     the message to be written
     * @param contentType the content type of the response
     * @throws IOException if an I/O error occurs
     */
    public static void writeResponse(HttpServletResponse response, String message, String contentType) throws IOException {
        response.setContentType(contentType);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }

    /**
     * Exports error data to an Excel file.
     *
     * @param response the HTTP response
     * @param errList  the list of error data
     * @param clazz    the type of the data
     * @param <T>      the type parameter
     * @throws IOException if an I/O error occurs
     */
    public static <T> void exportErrorExcel(HttpServletResponse response, List<ExcelImportErrDto<T>> errList, Class<T> clazz) throws IOException {
        List<T> userResultList = errList.stream().map(ExcelImportErrDto::getObject).collect(Collectors.toList());
        List<Map<Integer, String>> errMsgList = errList.stream().map(ExcelImportErrDto::getCellMap).collect(Collectors.toList());
        if (!userResultList.isEmpty()) {
            EasyExcelUtils.webWriteExcel(response, userResultList, clazz, errMsgList, "导入错误信息");
        }
    }
}
