package org.springblade.modules.iot.file.entity;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mqttsnet.basic.annotation.echo.Echo;
import com.mqttsnet.basic.base.entity.Entity;
import com.mqttsnet.basic.interfaces.echo.EchoVO;
import org.springblade.modules.iot.file.enumeration.FileStorageType;
import org.springblade.modules.iot.model.constant.EchoApi;
import org.springblade.modules.iot.model.constant.EchoDictType;
import org.springblade.modules.iot.model.enumeration.base.FileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Map;

import static org.springblade.modules.iot.model.constant.Condition.LIKE;


/**
 * <p>
 * 实体类
 * 增量文件上传日志
 * </p>
 *
 * @author mqttsnet
 * @date 2021-06-30
 * @create [2021-06-30] [mqttsnet] [初始创建]
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("com_file")
@AllArgsConstructor
public class File extends Entity<Long> implements EchoVO {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private final Map<String, Object> echoMap = MapUtil.newHashMap();

    /**
     * 业务类型
     */
    @TableField(value = "biz_type")
    private String bizType;

    /**
     * 文件类型
     */
    @TableField(value = "file_type", condition = LIKE)
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.FileType)
    private FileType fileType;

    /**
     * 存储类型
     * LOCAL FAST_DFS MIN_IO ALI
     */
    @TableField(value = "storage_type", condition = LIKE)
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.FileStorageType)
    private FileStorageType storageType;


    /**
     * 桶
     */
    @TableField(value = "bucket", condition = LIKE)
    private String bucket;

    /**
     * 文件相对地址
     */
    @TableField(value = "path", condition = LIKE)
    private String path;

    /**
     * 文件访问地址
     */
    @TableField(value = "url", condition = LIKE)
    private String url;

    /**
     * 唯一文件名
     */
    @TableField(value = "unique_file_name", condition = LIKE)
    private String uniqueFileName;

    /**
     * 文件md5
     */
    @TableField(value = "file_md5", condition = LIKE)
    private String fileMd5;

    /**
     * 文件SHA256
     */
    @TableField(value = "file_sha256", condition = LIKE)
    private String fileSha256;

    /**
     * 原始文件名
     */
    @TableField(value = "original_file_name", condition = LIKE)
    private String originalFileName;

    /**
     * 文件类型
     */
    @TableField(value = "content_type", condition = LIKE)
    private String contentType;

    /**
     * 后缀
     */
    @TableField(value = "suffix", condition = LIKE)
    private String suffix;

    /**
     * 大小
     */
    @TableField(value = "size_")
    private Long size;

    /** 租户ID，及用于记录上传者属于哪个租户 */
    @TableField(value = "tenant_id")
    private Long tenantId;


    @Builder
    public File(Long id, LocalDateTime createdTime, Long createdBy, LocalDateTime updatedTime, Long updatedBy,
                String bizType, FileType fileType, FileStorageType storageType, String bucket,
                String path, String url, String uniqueFileName, String fileMd5, String fileSha256, String originalFileName, String contentType,
                String suffix, Long size, Long tenantId) {
        this.id = id;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
        this.updatedBy = updatedBy;
        this.bizType = bizType;
        this.fileType = fileType;
        this.storageType = storageType;
        this.bucket = bucket;
        this.path = path;
        this.url = url;
        this.tenantId = tenantId;
        this.uniqueFileName = uniqueFileName;
        this.fileMd5 = fileMd5;
        this.fileSha256 = fileSha256;
        this.originalFileName = originalFileName;
        this.contentType = contentType;
        this.suffix = suffix;
        this.size = size;
    }

}
