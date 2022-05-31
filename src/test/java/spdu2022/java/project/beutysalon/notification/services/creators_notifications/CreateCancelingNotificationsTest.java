package spdu2022.java.project.beutysalon.notification.services.creators_notifications;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import spdu2022.java.project.beutysalon.entities.User;
import spdu2022.java.project.beutysalon.notification.controllers.dto.UsersNotificationBySalonIdDTO;
import spdu2022.java.project.beutysalon.notification.models.Notification;
import spdu2022.java.project.beutysalon.notification.models.NotificationType;
import spdu2022.java.project.beutysalon.notification.persistence.reposetories.GetBookingServiceRepository;
import spdu2022.java.project.beutysalon.notification.services.NotificationMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCancelingNotificationsTest {
    @Mock
    private GetBookingServiceRepository bookingServiceRepository;
    @Mock
    private NotificationMapper mapper;
    @InjectMocks
    private CreateCancelingNotifications creator;

    @Test
    @DisplayName("Method create notifications. Result should not contain duplicates")
    void createNotifications() {
        String email1 = "argosumy@gmail.com";
        String email2 = "olenka@gmail.com";
        when(bookingServiceRepository.getBookingBySalonId(1)).thenReturn(Mockito.mock(List.class));
        when(mapper.mapToNotifications(anyList(), any(UsersNotificationBySalonIdDTO.class))).thenReturn(getNotifications(email1, email1, email2));
        Set<Notification> result = creator.createNotifications(getDto());
        assertEquals(2, result.size());
    }

    @Test
    void getNotificationsType() {
        assertEquals(NotificationType.CANCELING_BOOKING, creator.getNotificationsType());
    }

    private UsersNotificationBySalonIdDTO getDto() {
        UsersNotificationBySalonIdDTO dto = new UsersNotificationBySalonIdDTO();
        dto.setSalonId(1);
        dto.setTypeNotification(NotificationType.CANCELING_BOOKING);
        dto.setContentNotification("Content notification");
        return dto;
    }

    private List<Notification> getNotifications(String... emails) {
        List<Notification> result = new ArrayList<>();
        for (String email : emails) {
            result.add(creatorNotification(email));
        }
        return result;
    }

    private Notification creatorNotification(String email) {
        Notification notification = new Notification(NotificationType.CANCELING_BOOKING);
        User user = new User();
        user.setEmail(email);
        notification.setToUser(user);
        return notification;
    }
}