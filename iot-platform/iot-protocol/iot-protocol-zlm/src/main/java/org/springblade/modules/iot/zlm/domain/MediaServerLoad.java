package org.springblade.modules.iot.zlm.domain;

import lombok.Data;

@Data
public class MediaServerLoad {

    private String id;

    private Object threadsLoad;

    private Object workThreadsLoad;
}
