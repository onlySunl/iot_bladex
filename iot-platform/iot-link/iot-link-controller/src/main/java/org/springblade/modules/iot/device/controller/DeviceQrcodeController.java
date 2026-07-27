package org.springblade.modules.iot.device.controller;

import org.springblade.common.utils.ArgumentAssert;
import org.springblade.modules.iot.device.service.DeviceQrcodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/**
 * -----------------------------------------------------------------------------
 * File Name: DeviceQrcodeController
 * -----------------------------------------------------------------------------
 * Description:
 * 设备二维码 Controller
 * -----------------------------------------------------------------------------
 *
 * @author xiaonannet
 * @version 1.0
 * -----------------------------------------------------------------------------
 * Revision History:
 * Date         Author          Version     Description
 * --------      --------     -------   --------------------
 * 2024/8/19       xiaonannet        1.0        Initial creation
 * -----------------------------------------------------------------------------
 * @email
 * @date 2024/8/19 17:54
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/deviceQrcode")
@Tag(name = "设备二维码管理")
public class DeviceQrcodeController {

    private final DeviceQrcodeService deviceQrcodeService;


    /**
     * 根据设备唯一标识生成带有Logo的设备二维码
     *
     * @param deviceIdentification 设备唯一标识
     * @param response             HTTP响应对象，用于将生成的二维码图片返回给客户端
     */
    @Operation(summary = "生成设备二维码", description = "根据设备唯一标识生成带有Logo的设备二维码")
    @GetMapping("/generateDeviceQrcode")
    @Parameters({
            @Parameter(name = "deviceIdentification", description = "设备唯一标识", required = true)
    })
    public void generateDeviceQrcode(
            @RequestParam("deviceIdentification") String deviceIdentification,
            HttpServletResponse response) {
        log.info("generateDeviceQrcode deviceIdentification: {}", deviceIdentification);
        ArgumentAssert.notBlank(deviceIdentification, "deviceIdentification cannot be null");
        try {
            byte[] qrcode = deviceQrcodeService.generateQrcode(deviceIdentification);

            // 设置响应头信息
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            String fileName = "device_qrcode_" + deviceIdentification + ".png";
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
            // 输出二维码图像
            try (OutputStream os = response.getOutputStream()) {
                os.write(qrcode);
                os.flush();
            }

            log.info("设备 [{}] 的二维码生成成功", deviceIdentification);

        } catch (FileNotFoundException e) {
            log.error("生成设备 [{}] 的二维码时，Logo文件未找到: {}", deviceIdentification, e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            handleErrorResponse(response, "Logo文件未找到：" + e.getMessage());
        } catch (IOException e) {
            log.error("生成设备 [{}] 的二维码时发生I/O错误: {}", deviceIdentification, e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            handleErrorResponse(response, "生成二维码时发生I/O错误：" + e.getMessage());
        } catch (Exception e) {
            log.error("生成设备 [{}] 的二维码时发生未知错误: {}", deviceIdentification, e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            handleErrorResponse(response, "生成二维码时发生错误：" + e.getMessage());
        }
    }

    /**
     * 处理错误响应
     *
     * @param response     响应对象
     * @param errorMessage 错误信息
     */
    private void handleErrorResponse(HttpServletResponse response, String errorMessage) {
        try {
            response.setContentType(MediaType.TEXT_PLAIN_VALUE);
            response.getWriter().write(errorMessage);
        } catch (IOException ioException) {
            log.error("写入错误响应时发生错误", ioException);
        }
    }

}
