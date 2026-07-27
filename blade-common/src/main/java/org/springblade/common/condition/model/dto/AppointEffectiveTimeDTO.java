package org.springblade.common.condition.model.dto;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class AppointEffectiveTimeDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
