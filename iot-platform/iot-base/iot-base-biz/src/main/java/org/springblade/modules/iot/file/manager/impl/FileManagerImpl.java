package org.springblade.modules.iot.file.manager.impl;

import org.springblade.core.mvc.manager.impl.SuperManagerImpl;
import org.springblade.modules.iot.file.entity.File;
import org.springblade.modules.iot.file.manager.FileManager;
import org.springblade.modules.iot.file.mapper.FileMapper;
import org.springblade.modules.iot.file.vo.result.FileResultVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件
 *
 * @author mqttsnet
 * @date 2021/10/31 10:24
 */
@Service
public class FileManagerImpl extends SuperManagerImpl<FileMapper, File> implements FileManager {
    @Override
    public List<FileResultVO> listByBizIdAndBizType(Long bizId, String bizType) {
        return baseMapper.listByBizIdAndBizType(bizId, bizType);
    }
}
