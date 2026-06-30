package org.springblade.modules.iot.domain;

import lombok.Data;

@Data
public class MediaServerLoad {

    private Long id;

    private Object threadsLoad;

    private Object workThreadsLoad;
}
