package org.springblade.core.easyexcel;

import lombok.Getter;

import java.util.Map;
import java.util.Optional;

/**
 * -----------------------------------------------------------------------------
 * File Name: ExcelImportErrDto
 * -----------------------------------------------------------------------------
 * Description:
 * This class represents an error DTO for Excel import.
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
 * @date 2024/6/19 19:12
 */
@Getter
public class ExcelImportErrDto<T> {

    private T object;
    private Map<Integer, String> cellMap;

    public ExcelImportErrDto() {
        this.cellMap = null;
    }

    public ExcelImportErrDto(T object, Map<Integer, String> cellMap) {
        this.object = object;
        this.cellMap = cellMap;
    }

    @Override
    public String toString() {
        return "ExcelImportErrDto{" +
               "object=" + object +
               ", cellMap=" + cellMap +
               '}';
    }

    public void setObject(T object) {
        this.object = object;
    }

    public void setCellMap(Map<Integer, String> cellMap) {
        this.cellMap = cellMap;
    }

    /**
     * Gets the object wrapped in an Optional.
     *
     * @return the object as an Optional
     */
    public Optional<T> getObjectOptional() {
        return Optional.ofNullable(object);
    }

    /**
     * Gets the cell map wrapped in an Optional.
     *
     * @return the cell map as an Optional
     */
    public Optional<Map<Integer, String>> getCellMapOptional() {
        return Optional.ofNullable(cellMap);
    }
}