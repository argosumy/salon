package spdu2022.java.project.beutysalon.log_book_services.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Date pattern:'yyyy-MM-dd' Date must be in future")
class LogBookServicesControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    LogBookServicesController controller;

    @Test
    @DisplayName("Dates must be in future")
    void getLogBookServicesBySalonId() throws Exception {
        String url = createUrlBySalonId(1, LocalDate.now().toString(), LocalDate.now().plusDays(3).toString());
        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        url = createUrlBySalonId(1, "2022-01-01", "2022-01-02");
        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    @Test
    @DisplayName("Dates must be in future")
    void getLogBookServicesByCity() throws Exception {
        String url = createUrlByCity("Sumy", LocalDate.now().toString(), LocalDate.now().plusDays(3).toString());
        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        url = createUrlByCity("Sumy","2022-01-01", "2022-01-02");
        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    private String createUrlBySalonId(int salonId, String start, String end) {
        return "http://localhost:8080/api/v1/salons/" + salonId + "/log-book-services" +
                "?end-period=" + end + "&start-period=" + start;
    }

    private String createUrlByCity(String city, String start, String end) {
        return "http://localhost:8080/api/v1/salons/log-book-services" +
                "?city=" + city + "&end-period=" + end + "&start-period=" + start;
    }

}