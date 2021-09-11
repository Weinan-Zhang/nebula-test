package io.nebula.test.web.controller;

import io.nebula.test.model.CsvDataDto;
import io.nebula.test.model.LogDto;
import io.nebula.test.model.ResponseDto;
import io.nebula.test.model.TestResponseDto;
import io.nebula.test.service.LogAndErrorCountService;
import io.nebula.test.service.RunTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static io.nebula.test.web.controller.Constant.*;

@RestController
public class TestController {
    private String[] admins = {"admin_1", "admin_2", "admin_3"};
    private final List<String> ADMINS = Arrays.asList(admins);

    @Autowired
    private RunTaskService runTaskService;
    @Autowired
    private LogAndErrorCountService logAndErrorCountService;

    @GetMapping(value="start")
    public ResponseDto<List<TestResponseDto>> start() {
        ResponseDto<List<TestResponseDto>> responseDto;
        boolean isSuccessful = true;
        List<TestResponseDto> responses = this.runTaskService.startWork();
        if(Objects.nonNull(responses) && responses.size()>0) {
            responseDto = ResponseDto.buildResponse(HttpStatus.OK.value(), TASK_COMPLETED_WITH_SUCCESS, responses);
        }
        else {
            responseDto = ResponseDto.buildResponse(HttpStatus.NOT_FOUND.value(), NO_TASK_FOUND, null);
            isSuccessful = false;
        }

        logVisit(isSuccessful);
        if(!isSuccessful) {
            this.logAndErrorCountService.countError("start");
        }
        return responseDto;
    }

    @PostMapping(value="exportcsv")
    public ResponseDto<Object> exportCsv(@RequestBody CsvDataDto csvDataDto) {
        ResponseDto<Object> responseDto;
        boolean flag = this.runTaskService.generateCsv(csvDataDto.getApiCallData(), csvDataDto.getApiKey(), csvDataDto.getApiValue());
        if(flag) {
            responseDto = ResponseDto.buildResponse(HttpStatus.CREATED.value(), DATA_WRITTEN_IN_CSV_WITH_SUCCESS, null);
        }
        else {
            responseDto = ResponseDto.buildResponse(HttpStatus.NOT_FOUND.value(), DATA_WRITTEN_IN_CSV_WITH_FAILURE, null);
        }

        logVisit(flag);
        if(!flag) {
            this.logAndErrorCountService.countError("exportcsv");
        }
        return responseDto;
    }

    private void logVisit(boolean isSuccessful) {
        Collections.shuffle(ADMINS);
        LogDto logDto = new LogDto(ADMINS.get(0),
                new Date(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                isSuccessful);
        this.logAndErrorCountService.logApiVisit(logDto);
    }
}
