package org.springblade.modules.iot.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CloudRecordUrl {

    private String filePath;
    private String playUrl;
    private String downloadUrl;
    private String fileName;
    private Long id;

}
