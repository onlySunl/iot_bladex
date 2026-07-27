package org.springblade.common.groovy.entity;
import lombok.Data;
import java.util.Map;
@Data
public class ExecuteParams {
    private String scriptId;
    private Map<String, Object> params;
}
