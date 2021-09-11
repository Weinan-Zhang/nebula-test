package io.nebula.test.web.controller;

import io.nebula.test.TestApplication;
import io.nebula.test.persistence.ErrorCountsRepository;
import io.nebula.test.persistence.LogRepository;
import io.nebula.test.persistence.converter.LogDtoToLogConverter;
import io.nebula.test.service.LogAndErrorCountService;
import io.nebula.test.service.RunTaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {TestApplication.class, RunTaskService.class})
@WebMvcTest(controllers = TestController.class)
@ExtendWith(MockitoExtension.class)
public class TestControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    RunTaskService runTaskServiceMock;
    @MockBean
    LogAndErrorCountService logAndErrorCountService;
    @MockBean
    ErrorCountsRepository errorCountsRepository;
    @MockBean
    LogRepository logRepository;
    @MockBean
    LogDtoToLogConverter logDtoToLogConverter;

    @Test
    public void testGetEndpoint() throws Exception {
        // given
        doNothing().when(logAndErrorCountService).logApiVisit(any());
        doNothing().when(logAndErrorCountService).countError(any());

        // when and then
        this.mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/start")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload[0].workerName").value("test-worker-1"))
                .andExpect(jsonPath("$.payload[3].cpuUsage").value("13%"))
                .andExpect(jsonPath("$.payload.size()").value(6));
    }
}
