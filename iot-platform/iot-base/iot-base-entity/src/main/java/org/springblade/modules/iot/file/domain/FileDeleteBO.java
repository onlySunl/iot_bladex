package org.springblade.modules.iot.file.domain;

import org.springblade.modules.iot.file.enumeration.FileStorageType;
import lombok.Builder;
import lombok.Data;

/**
 * 文件删除
 *
 * @author mqttsnet
 * @date 2019/05/07
 */
@Data
@Builder
public class FileDeleteBO {
    /**
     * 桶
     */
    private String bucket;
    /**
     * 相对路径
     */
    private String path;
    /**
     * 存储类型
     */
    private FileStorageType storageType;
}
