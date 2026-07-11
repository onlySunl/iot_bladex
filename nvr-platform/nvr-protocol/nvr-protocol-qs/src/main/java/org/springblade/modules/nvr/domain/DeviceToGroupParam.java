package org.springblade.modules.nvr.domain;

import lombok.Data;

import java.util.List;

@Data
public class DeviceToGroupParam {

    private String parentId;
    private String businessGroup;
    private List<Long> deviceIds;
    private Boolean all;
}
