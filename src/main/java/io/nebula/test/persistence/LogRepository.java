package io.nebula.test.persistence;

import io.nebula.test.persistence.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {
    Log save(Log log);
}
