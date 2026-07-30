package org.springblade.modules.iot.file.facade.impl;

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
