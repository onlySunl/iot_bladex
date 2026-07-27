package org.springblade.modules.iot.device.easyexcel;

import org.springblade.common.easyexcel.ExcelCheckResult;

import java.util.List;

/**
 * -----------------------------------------------------------------------------
 * File Name: DeviceEasyexcelService
 * -----------------------------------------------------------------------------
 * Description:
 * 设备档案导入导出业务接口
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/6/20       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email 13733918655@163.com
 * @date 2024/6/20 18:03
 */
public interface DeviceEasyExcelService {


    /**
     * check import excel data
     *
     * @param deviceImportDataList device import data list
     * @return {@link ExcelCheckResult <DeviceImportData>} check result
     */
    ExcelCheckResult<DeviceImportData> checkImportExcel(List<DeviceImportData> deviceImportDataList);

}
