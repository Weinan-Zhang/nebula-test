package io.nebula.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDto {
    private String adminId;
    private Date callDate;
    private Timestamp callTime;
    private boolean result;
}
