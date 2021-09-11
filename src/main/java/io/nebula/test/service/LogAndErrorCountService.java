package io.nebula.test.service;

import io.nebula.test.model.ErrorCountsDto;
import io.nebula.test.model.LogDto;
import io.nebula.test.persistence.ErrorCountsRepository;
import io.nebula.test.persistence.LogRepository;
import io.nebula.test.persistence.converter.ErrorCountsDtoToErrorCountsConverter;
import io.nebula.test.persistence.converter.ErrorCountsToErrorCountsDtoConverter;
import io.nebula.test.persistence.converter.LogDtoToLogConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LogAndErrorCountService {
    @Autowired
    private ErrorCountsRepository errorCountsRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private ErrorCountsToErrorCountsDtoConverter errorCountsToErrorCountsDtoConverter;
    @Autowired
    private ErrorCountsDtoToErrorCountsConverter errorCountsDtoToErrorCountsConverter;
    @Autowired
    private LogDtoToLogConverter logDtoToLogConverter;

    @Transactional
    public void logApiVisit(LogDto logDto) {
        this.logRepository.save(this.logDtoToLogConverter.convert(logDto));
    }

    @Transactional
    public void countError(String apiName){
        ErrorCountsDto errorCountsDto = this.errorCountsToErrorCountsDtoConverter.convert(this.errorCountsRepository.findByApiName(apiName));
        errorCountsDto.setErrorCounts(errorCountsDto.getErrorCounts() + 1);
        this.errorCountsRepository.save(this.errorCountsDtoToErrorCountsConverter.convert(errorCountsDto));
    }
}
