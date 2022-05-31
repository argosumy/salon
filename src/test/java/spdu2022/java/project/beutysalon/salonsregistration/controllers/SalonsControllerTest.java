package spdu2022.java.project.beutysalon.salonsregistration.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import spdu2022.java.project.beutysalon.salonsregistration.services.SalonsGetService;
import spdu2022.java.project.beutysalon.salonsregistration.services.SalonsModificationService;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SalonsController.class)
class SalonsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SalonsModificationService salonsModificationService;
    @MockBean
    private SalonsGetService salonsGetService;
    @MockBean
    private SalonMapper salonMapper;

    @Test
    void getSalonById() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/v1/salons/-1")).andExpect(status().is4xxClientError());
    }

    @Test
    void getAllSalonsFromCity() throws Exception {
        when(salonsGetService.getAllSalonsFromCity("Sumy")).thenReturn(new ArrayList<>());
        mockMvc.perform(get("http://localhost:8080/api/v1/salons/?city=Sumy")).andExpect(status().is2xxSuccessful());
    }

    @Test
    void createNewSalon() throws Exception {
        String body = "{\"salonName\":\"Lud\",\"cityLocation\":\"Sumy\",\"phone\":\"+380994869938\"}";
        mockMvc.perform(post("http://localhost:8080/api/v1/salons/")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201));

        body = "{\"salonName\":\"Lud\",\"cityLocation\":\"Sumy\",\"phone\":\"+3809948699\"}";
        mockMvc.perform(post("http://localhost:8080/api/v1/salons/")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void deleteSalonById() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/api/v1/salons/-1")).andExpect(status().is4xxClientError());
    }

    @Test
    void updateSalon() throws Exception {
        String body = "{\"salonName\":\"Lud\",\"cityLocation\":\"Sumy\",\"phone\":\"+380994869938\"}";
        mockMvc.perform(put("http://localhost:8080/api/v1/salons/1")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(put("http://localhost:8080/api/v1/salons/-1")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}