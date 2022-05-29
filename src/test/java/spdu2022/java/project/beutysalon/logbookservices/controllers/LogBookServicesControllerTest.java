package spdu2022.java.project.beutysalon.logbookservices.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import spdu2022.java.project.beutysalon.logbookservices.services.LogBookSelectService;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LogBookServicesController.class)
@DisplayName("Date pattern:'yyyy-MM-dd' Date must be in future")
class LogBookServicesControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    LogBookSelectService service;

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
    //http://localhost:8080/api/v1/salons/1/log-book-services?end-period=2022-05-05&start-period=2022-05-05
    private String createUrlByCity(String city, String start, String end) {
        return "http://localhost:8080/api/v1/salons/log-book-services" +
                "?city=" + city + "&end-period=" + end + "&start-period=" + start;
    }
}