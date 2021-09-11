package io.nebula.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorCountsDto {
    private int id;
    private String apiName;
    private int errorCounts;
}
