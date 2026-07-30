package org.springblade.modules.iot.file.facade.impl;


import org.springblade.basic.base.R;
import org.springblade.basic.base.R;
import org.springblade.modules.iot.file.api.FileApi;
import org.springblade.modules.iot.file.enumeration.FileStorageType;
import org.springblade.modules.iot.file.facade.FileFacade;
import org.springblade.modules.iot.file.vo.result.FileResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
public class FileFacadeImpl implements FileFacade {
    @Autowired
    @Lazy
    private FileApi fileApi;

    @Override
    public FileResultVO upload(MultipartFile file, String bizType, String bucket, FileStorageType storageType) {
        R<FileResultVO> result = fileApi.upload(file, bizType, bucket, storageType);
        return result.getData();
    }

    @Override
    public R<Map<Long, String>> findUrlFromDefById(List<Long> ids) {
        return fileApi.findUrlFromDefById(ids);
    }

    @Override
    public R<Map<Long, FileResultVO>> findInfoFromDefById(List<Long> ids) {
        return fileApi.findInfoFromDefById(ids);
    }
}
