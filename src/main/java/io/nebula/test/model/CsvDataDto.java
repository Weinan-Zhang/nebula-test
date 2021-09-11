package io.nebula.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsvDataDto {
    private String apiCallData;
    private String ApiKey;
    private String ApiValue;
}
