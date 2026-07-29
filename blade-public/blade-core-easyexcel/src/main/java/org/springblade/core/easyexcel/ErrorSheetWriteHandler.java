package org.springblade.core.easyexcel;

import cn.idev.excel.write.handler.AbstractRowWriteHandler;
import cn.idev.excel.write.metadata.holder.WriteSheetHolder;
import cn.idev.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * -----------------------------------------------------------------------------
 * File Name: ErrorSheetWriteHandler
 * -----------------------------------------------------------------------------
 * Description: Handler for writing error messages as comments in an Excel sheet.
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
 * @date 2024/6/19 19:09
 */
public class ErrorSheetWriteHandler extends AbstractRowWriteHandler {

    private final List<Map<Integer, String>> errMsgList;

    /**
     * Constructs a new handler with the given error message list.
     *
     * @param errMsgList a list of error messages for each row
     */
    public ErrorSheetWriteHandler(List<Map<Integer, String>> errMsgList) {
        this.errMsgList = errMsgList;
    }

    @Override
    public void afterRowDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
                                Integer relativeRowIndex, Boolean isHead) {
        if (!Boolean.TRUE.equals(isHead)) {
            Sheet sheet = writeSheetHolder.getSheet();
            Map<Integer, String> rowErrMap = errMsgList.get(relativeRowIndex);
            rowErrMap.forEach((cellIndex, message) ->
                    setPostil(sheet, relativeRowIndex, cellIndex, message));
        }
    }

    /**
     * Sets a comment on a specific cell with the given message.
     *
     * @param sheet            the sheet to add the comment to
     * @param relativeRowIndex the index of the row
     * @param cellIndex        the index of the cell
     * @param msg              the message to add as a comment
     */
    private void setPostil(Sheet sheet, Integer relativeRowIndex, Integer cellIndex, String msg) {
        Workbook workbook = sheet.getWorkbook();
        CellStyle cellStyle = createErrorCellStyle(workbook);
        Drawing<?> drawingPatriarch = sheet.createDrawingPatriarch();
        Comment comment = createComment(drawingPatriarch, msg);

        Optional.ofNullable(sheet.getRow(relativeRowIndex + 1))
                .map(row -> row.getCell(cellIndex))
                .ifPresentOrElse(
                        cell -> {
                            cell.setCellComment(comment);
                            cell.setCellStyle(cellStyle);
                        },
                        () -> {
                            Row row = sheet.createRow(relativeRowIndex + 1);
                            Cell cell = row.createCell(cellIndex);
                            cell.setCellComment(comment);
                            cell.setCellStyle(cellStyle);
                        }
                );
    }

    /**
     * Creates a cell style for error cells.
     *
     * @param workbook the workbook to create the style in
     * @return the created cell style
     */
    private CellStyle createErrorCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    /**
     * Creates a comment with the given message.
     *
     * @param drawing the drawing patriarch to create the comment in
     * @param msg     the message for the comment
     * @return the created comment
     */
    private Comment createComment(Drawing<?> drawing, String msg) {
        Comment comment = drawing.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, 0, 0, 2, 2));
        comment.setString(new XSSFRichTextString(msg));
        return comment;
    }
}
