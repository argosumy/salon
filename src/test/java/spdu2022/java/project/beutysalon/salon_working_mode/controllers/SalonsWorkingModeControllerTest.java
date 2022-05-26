package spdu2022.java.project.beutysalon.salon_working_mode.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import spdu2022.java.project.beutysalon.salons_working_mode.controllers.SalonsWorkingModeController;
import spdu2022.java.project.beutysalon.salons_working_mode.services.SalonsWorkingModeModificationService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SalonsWorkingModeController.class)
class SalonsWorkingModeControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    SalonsWorkingModeModificationService controller;

    @Test
    @DisplayName("Date pattern:''yyyy-MM-dd''. Date must be in future. Time is valid format: 10:00, 1:59, 15:01, 00:09")
    void createWorkingMode() throws Exception {
        String body = buildBody("2023-05-15", "09:00", "18:00");
        mockMvc.perform(post("http://localhost:8080/api/v1/working-mode/salons/1/days")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201));

        body = buildBody("2020-03-15", "09:00", "20:00");
        mockMvc.perform(post("http://localhost:8080/api/v1/working-mode/salons/1/days")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    private String buildBody(String date, String start, String end) {
        return "{" +
                "  \"salonId\": 0,\n" +
                "  \"salonWorkingPeriodList\": [\n" +
                "    {\n" +
                "      \"workingDay\": \"" + date + "\",\n" +
                "      \"startWorking\": \"" + start + "\",\n" +
                "      \"endWorking\": \"" + end + "\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
}