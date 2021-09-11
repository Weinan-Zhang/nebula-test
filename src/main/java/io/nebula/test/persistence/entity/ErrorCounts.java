package io.nebula.test.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="error_counts")
@Data
@NoArgsConstructor
public class ErrorCounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="api_name")
    private String apiName;
    @Column(name="error_counts")
    private int errorCounts;
}
