package org.springblade.modules.nvr.domain;

import lombok.Data;

@Data
public class MediaServerLoad {

    private Long id;

    private Object threadsLoad;

    private Object workThreadsLoad;
}
