package org.springblade.modules.iot.file.facade;
import org.springblade.basic.base.R;
import org.springblade.modules.iot.file.enumeration.FileStorageType;
import org.springblade.modules.iot.file.vo.result.FileResultVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 文件接口
 *
 * @author zuihou
 * @since 2024年09月20日10:37:25
 */
public interface FileFacade {

    /**
     * 通过feign-form 实现文件 跨服务上传
     *
     * @param file        文件
     * @param bizType     业务类型
     * @param bucket      桶(可为空字符串,默认为系统配置的桶)
     * @param storageType 存储类型(可为空,默认为系统配置的存储类型)
     * @return 文件信息
     */
    FileResultVO upload(MultipartFile file, String bizType, String bucket, FileStorageType storageType);


    /**
     * 根据文件ID列表获取文件URL
     *
     * @param ids 文件ID列表
     * @return {@link R <Map<Long,String>>} 文件ID对应的文件URL
     */
    R<Map<Long, String>> findUrlFromDefById(List<Long> ids);

    /**
     * 根据文件ID列表获取文件详细信息
     *
     * @param ids 文件id列表
     * @return {@link R<Map<Long,FileResultVO>>} 文件id对应的文件信息
     */
    R<Map<Long, FileResultVO>> findInfoFromDefById(List<Long> ids);
}
