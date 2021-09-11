package io.nebula.test.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="log")
@Data
@NoArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="admin_id")
    private String adminId;
    @Column(name="call_date")
    private Date callDate;
    @Column(name="call_time")
    private Timestamp callTime;
    @Column(name="result")
    private boolean result;
}
