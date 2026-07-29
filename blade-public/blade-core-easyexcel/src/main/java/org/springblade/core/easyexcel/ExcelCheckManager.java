package org.springblade.core.easyexcel;

import java.util.List;

/**
 * -----------------------------------------------------------------------------
 * File Name: ExcelCheckManager
 * -----------------------------------------------------------------------------
 * Description:
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
 * @date 2024/6/19 19:10
 */
public interface ExcelCheckManager<T> {
    ExcelCheckResult<T> checkImportExcel(List<T> objects);
}
