package io.nebula.test.service;

import io.nebula.test.model.TestResponseDto;

import java.util.concurrent.Callable;

public class TestCallableTask implements Callable<TestResponseDto> {
    TestResponseDto testResponseDto;
    @Override
    public TestResponseDto call() throws Exception {
       testResponseDto = TestResponseDto.builder()
                .workerId(Thread.currentThread().getId())
                .workerName(Thread.currentThread().getName())
                .cpuUsage("13%")
                .memoryUsage("30%")
                .gpuUsage("not in use").build();
       return testResponseDto;
    }
}
