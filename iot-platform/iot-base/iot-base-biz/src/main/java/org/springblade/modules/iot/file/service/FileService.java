package org.springblade.modules.iot.file.service;

import org.springblade.core.mvc.service.SuperService;
import org.springblade.modules.iot.file.entity.File;
import org.springblade.modules.iot.file.vo.param.FileUploadVO;
import org.springblade.modules.iot.file.vo.result.FileResultVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务接口
 * 增量文件上传日志
 * </p>
 *
 * @author mqttsnet
 * @date 2021-06-30
 * @create [2021-06-30] [mqttsnet] [初始创建]
 */
public interface FileService extends SuperService<Long, File> {

    /**
     * 根据业务id 和 业务类型 查询附件
     * <p>
     * 返回值为： [附件, ...]
     *
     * @param bizId   业务id
     * @param bizType 业务类型
     * @return 附件
     */
    List<FileResultVO> listByBizIdAndBizType(Long bizId, String bizType);

    /**
     * 上传附件
     *
     * @param file         文件
     * @param attachmentVO 参数
     * @return 附件
     */
    FileResultVO upload(MultipartFile file, FileUploadVO attachmentVO);

    /**
     * 获取文件的临时访问链接
     *
     * @param paths 文件相对路径
     * @return
     */
    Map<String, String> findUrlByPath(List<String> paths);

    /**
     * 获取文件的临时访问链接
     *
     * @param ids 文件id
     * @return
     */
    Map<Long, String> findUrlById(List<Long> ids);

    /**
     * 根据文件ID列表获取文件详细信息
     *
     * @param ids 文件id列表
     * @return 文件id对应的文件信息
     */
    Map<Long, FileResultVO> findByIds(List<Long> ids);

    /**
     * 下载文件
     *
     * @param request  请求头
     * @param response 响应头
     * @param ids      文件id
     * @throws Exception
     */
    void download(HttpServletRequest request, HttpServletResponse response, List<Long> ids) throws Exception;

    /**
     * 下载文件
     *
     * @param request  请求头
     * @param response 响应头
     * @param id      文件id
     * @throws Exception
     */
    void download(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception;
}
