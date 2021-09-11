package io.nebula.test.persistence;

import io.nebula.test.persistence.entity.ErrorCounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorCountsRepository extends JpaRepository<ErrorCounts, Integer> {
    ErrorCounts findByApiName(String apiName);
    ErrorCounts save(ErrorCounts errorCounts);
}
