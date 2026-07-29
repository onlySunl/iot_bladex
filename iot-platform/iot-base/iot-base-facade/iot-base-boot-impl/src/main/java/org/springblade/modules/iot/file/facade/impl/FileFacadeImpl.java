package org.springblade.modules.iot.file.facade.impl;


import com.mqttsnet.basic.base.R;
import org.springblade.modules.iot.file.enumeration.FileStorageType;
import org.springblade.modules.iot.file.facade.FileFacade;
import org.springblade.modules.iot.file.service.FileService;
import org.springblade.modules.iot.file.vo.param.FileUploadVO;
import org.springblade.modules.iot.file.vo.result.FileResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 文件接口
 *
 * @author zuihou
 * @since 2024年09月20日10:45:54
 */
@Service
@RequiredArgsConstructor
public class FileFacadeImpl implements FileFacade {
    private final FileService fileService;


    @Override
    public FileResultVO upload(MultipartFile file, String bizType, String bucket, FileStorageType storageType) {
        FileUploadVO fileUploadVO = new FileUploadVO();
        fileUploadVO.setBizType(bizType);
        fileUploadVO.setBucket(bucket);
        fileUploadVO.setStorageType(storageType);
        return fileService.upload(file, fileUploadVO);
    }

    @Override
    public R<Map<Long, String>> findUrlFromDefById(List<Long> ids) {
        return R.success(fileService.findUrlById(ids));
    }

    @Override
    public R<Map<Long, FileResultVO>> findInfoFromDefById(List<Long> ids) {
        return R.success(fileService.findByIds(ids));
    }
}
