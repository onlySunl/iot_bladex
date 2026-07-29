package org.springblade.modules.iot.file.api.fallback;

import com.mqttsnet.basic.base.R;
import org.springblade.modules.iot.file.api.FileApi;
import org.springblade.modules.iot.file.enumeration.FileStorageType;
import org.springblade.modules.iot.file.vo.result.FileResultVO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 熔断
 *
 * @author zuihou
 * @date 2019/07/25
 */
@Component
public class FileApiFallback implements FileApi {
    @Override
    public R<FileResultVO> upload(MultipartFile file, String bizType, String bucket, FileStorageType storageType) {
        return R.timeout();
    }

    @Override
    public R<Map<Long, String>> findUrlFromDefById(List<Long> ids) {
        return R.timeout();
    }

    @Override
    public R<Map<Long, FileResultVO>> findInfoFromDefById(List<Long> ids) {
        return R.timeout();
    }
}
