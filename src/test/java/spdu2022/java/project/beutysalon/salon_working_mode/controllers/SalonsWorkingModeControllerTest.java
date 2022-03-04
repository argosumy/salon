package spdu2022.java.project.beutysalon.salon_working_mode.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import spdu2022.java.project.beutysalon.salons_working_mode.controllers.SalonsWorkingModeController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SalonsWorkingModeControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    SalonsWorkingModeController controller;

    @Test
    @DisplayName("Date pattern:'dd:MM:yyyy'. Date must be in future. Time is valid format: 10:00, 1:59, 15:01, 00:09")
    void createWorkingMode() throws Exception {
        String body = buildBody("15:03:2022", "09:00", "18:00");
        mockMvc.perform(post("http://localhost:8080/api/v1/working-mode/salons/1")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201));

        body = buildBody("15:03:2020", "09:00", "20:00");
        mockMvc.perform(post("http://localhost:8080/api/v1/working-mode/salons/1")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());


    }

    private String buildBody(String date, String start, String end) {
        return "{" +
                "  \"salonId\": 0,\n" +
                "  \"salonWorkingMode\": [\n" +
                "    {\n" +
                "      \"date\": \"" + date + "\",\n" +
                "      \"startWorking\": \"" + start + "\",\n" +
                "      \"endWorking\": \"" + end + "\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
}