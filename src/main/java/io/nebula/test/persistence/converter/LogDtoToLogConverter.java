package io.nebula.test.persistence.converter;


import io.nebula.test.model.LogDto;
import io.nebula.test.persistence.entity.Log;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LogDtoToLogConverter implements Converter<LogDto, Log> {

    @Override
    public Log convert(LogDto logDto) {
        Log log = new Log();
        log.setAdminId(logDto.getAdminId());
        log.setCallDate(logDto.getCallDate());
        log.setCallTime(logDto.getCallTime());
        log.setResult(logDto.isResult());
        return log;
    }
}
