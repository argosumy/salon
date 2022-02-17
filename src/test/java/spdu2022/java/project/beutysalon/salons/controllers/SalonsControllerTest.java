package spdu2022.java.project.beutysalon.salons.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SalonsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SalonsController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void getSalonById() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/v1/salons/-1")).andExpect(status().is4xxClientError());
    }

    @Test
    void getAllSalonsFromCity() throws Exception {
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
        mockMvc.perform(delete("http://localhost:8080/api/v1/salons/1")).andExpect(status().is2xxSuccessful());
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