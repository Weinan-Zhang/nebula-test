package io.nebula.test.service;

import io.nebula.test.model.TestResponseDto;
import io.nebula.test.web.controller.exception.TestException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import static io.nebula.test.web.controller.Constant.*;

@Service
public class RunTaskService {
    private final int NUM_OF_WORKERS = 6;
    private final String CSV_FILE = "test-data.csv";

    private static List<TestResponseDto> responses = new ArrayList<>();

    @Autowired
    private static CSVPrinter csvPrinter;

    public List<TestResponseDto> startWork() {
        responses.clear();
        NameableThreadFactory threadFactory = new NameableThreadFactory("test-worker");
        ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_WORKERS, threadFactory);
        IntStream.range(0, NUM_OF_WORKERS).forEach(item -> {
            Future<TestResponseDto> future = executor.submit(new TestCallableTask());
            try {
                if(Objects.nonNull(future.get())) {
                    responses.add(future.get());
                }
            } catch (InterruptedException e) {
                throw new TestException(-1, PARALLEL_TASKS_INTERRUPTED);
            } catch (ExecutionException e) {
                throw new TestException(-1, TASKS_EXECUTION_WITH_FAILURE);
            }
        });
        executor.shutdown();
        return responses;
    }

    public boolean generateCsv(String apiData, String apiKey, String apiValue) {
        boolean flag = true;
        try {
            BufferedWriter writer;
            if(Files.exists(Paths.get(CSV_FILE))) {
                writer = Files.newBufferedWriter(
                        Paths.get(CSV_FILE),
                        StandardOpenOption.APPEND,
                        StandardOpenOption.WRITE);
                csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            }
            else {
                writer = Files.newBufferedWriter(
                        Paths.get(CSV_FILE),
                        StandardOpenOption.CREATE);
                csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("API_DATA", "API_KEY", "API_VALUE"));
            }

            csvPrinter.printRecord(apiData, apiKey, apiValue);
            csvPrinter.flush();

            csvPrinter.close();
            writer.close();

        } catch (IOException e) {
            throw new TestException(-1, CSV_SAVED_WITH_FAILURE);
        }
        return flag;
    }
}
