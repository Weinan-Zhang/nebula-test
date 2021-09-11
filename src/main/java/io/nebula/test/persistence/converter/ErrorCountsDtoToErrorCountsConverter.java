package io.nebula.test.persistence.converter;

import io.nebula.test.model.ErrorCountsDto;
import io.nebula.test.persistence.entity.ErrorCounts;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ErrorCountsDtoToErrorCountsConverter implements Converter<ErrorCountsDto, ErrorCounts> {
    @Override
    public ErrorCounts convert(ErrorCountsDto errorCountsDto) {
        ErrorCounts errorCounts = new ErrorCounts();
        errorCounts.setApiName(errorCountsDto.getApiName());
        errorCounts.setErrorCounts(errorCountsDto.getErrorCounts());
        errorCounts.setId(errorCountsDto.getId());
        return errorCounts;
    }
}
