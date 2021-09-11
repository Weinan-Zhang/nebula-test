package io.nebula.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestResponseDto {
    private long workerId;
    private String workerName;
    private String cpuUsage;
    private String memoryUsage;
    private String gpuUsage;
}
