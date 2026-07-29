package org.springblade.core.easyexcel;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * -----------------------------------------------------------------------------
 * File Name: ExcelCheckResult
 * -----------------------------------------------------------------------------
 * Description:
 * This class represents the result of an Excel data check.
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
 * @date 2024/6/19 19:11
 */
@Data
@RequiredArgsConstructor
public class ExcelCheckResult<T> {

    private List<T> successDtos;
    private List<ExcelImportErrDto<T>> errDtos;

    /**
     * Constructor with both success and error DTOs.
     *
     * @param successDtos the list of successful DTOs
     * @param errDtos     the list of error DTOs
     */
    public ExcelCheckResult(List<T> successDtos, List<ExcelImportErrDto<T>> errDtos) {
        this.successDtos = successDtos;
        this.errDtos = errDtos;
    }

    /**
     * Constructor with only error DTOs.
     *
     * @param errDtos the list of error DTOs
     */
    public ExcelCheckResult(List<ExcelImportErrDto<T>> errDtos) {
        this.successDtos = new ArrayList<>();
        this.errDtos = errDtos;
    }
}