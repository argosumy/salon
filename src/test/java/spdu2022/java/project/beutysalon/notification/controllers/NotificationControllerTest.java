package spdu2022.java.project.beutysalon.notification.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import spdu2022.java.project.beutysalon.notification.services.UsersNotificationService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UsersNotificationService notificationServices;

    @Test
    void notificationUsersBySalonId() throws Exception {
        String body = "{\"contentNotification\": \"Canceling booking\", \"salonId\": 1, \"typeNotification\": \"CANCELING_BOOKING\"}";
        mockMvc.perform(post("http://localhost:8080/api/v1/salons/1/users/notifications")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }
}