package org.springblade.modules.iot.file.controller;

import org.springblade.basic.context.ContextUtil;
import org.springblade.basic.interfaces.echo.EchoService;
import org.springblade.core.mvc.controller.DeleteController;
import org.springblade.core.mvc.controller.QueryController;
import org.springblade.core.mvc.controller.SuperSimpleController;
import org.springblade.core.mvc.request.PageParams;
import org.springblade.modules.iot.file.entity.File;
import org.springblade.modules.iot.file.service.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 增量文件上传日志
 * </p>
 *
 * @author mqttsnet
 * @date 2021-06-30
 * @create [2021-06-30] [mqttsnet] [初始创建]
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@Tag(name = "com_file表增删改")
public class FileController extends SuperSimpleController<FileService, Long, File>
        implements QueryController<Long, File, File, File>, DeleteController<Long, File> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public Class<File> getResultVOClass() {
        return File.class;
    }

    @Override
    public void handlerQueryParams(PageParams<File> params) {
        ContextUtil.setDefTenantId();
    }

}
