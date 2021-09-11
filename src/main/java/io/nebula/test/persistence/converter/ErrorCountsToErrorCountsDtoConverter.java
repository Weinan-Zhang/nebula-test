package io.nebula.test.persistence.converter;

import io.nebula.test.model.ErrorCountsDto;
import io.nebula.test.persistence.entity.ErrorCounts;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ErrorCountsToErrorCountsDtoConverter implements Converter<ErrorCounts, ErrorCountsDto> {
    @Override
    public ErrorCountsDto convert(ErrorCounts errorCounts) {
        ErrorCountsDto errorCountsDto = new ErrorCountsDto();
        errorCountsDto.setApiName(errorCounts.getApiName());
        errorCountsDto.setErrorCounts(errorCounts.getErrorCounts());
        errorCountsDto.setId(errorCounts.getId());
        return null;
    }
}
