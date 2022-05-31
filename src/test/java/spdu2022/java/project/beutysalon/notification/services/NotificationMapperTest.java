package spdu2022.java.project.beutysalon.notification.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spdu2022.java.project.beutysalon.entities.*;
import spdu2022.java.project.beutysalon.notification.controllers.dto.UsersNotificationBySalonIdDTO;
import spdu2022.java.project.beutysalon.notification.models.Notification;
import spdu2022.java.project.beutysalon.notification.models.NotificationType;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationMapperTest {
    @Test
    @DisplayName("Method must convert booking services to notification.")
    void mapToNotifications() {
        NotificationMapper notificationMapper = new NotificationMapper();
        List<BookedService> bookedServices = getAllBookedService();
        List<Notification> result = notificationMapper.mapToNotifications(bookedServices, getDto());
        assertEquals(bookedServices.size(), result.size());
    }

    private UsersNotificationBySalonIdDTO getDto() {
        UsersNotificationBySalonIdDTO dto = new UsersNotificationBySalonIdDTO();
        dto.setSalonId(1);
        dto.setTypeNotification(NotificationType.CANCELING_BOOKING);
        dto.setContentNotification("Content Notification");
        return dto;
    }

    private List<BookedService> getAllBookedService() {
        List<BookedService> result = new ArrayList<>();
        result.add(creatorBookedService(9));
        result.add(creatorBookedService(12));
        return result;
    }

    private BookedService creatorBookedService(int startHour) {
        final Salon salon = new Salon();
        salon.setId(1);
        salon.setSalonName("Ludmila");
        salon.setPhone("+380994869938");

        final Staff staff = new Staff();
        staff.setId(1);
        staff.setSalonId(1);

        final User user = new User();
        user.setId(1);
        user.setFirstName("Otenko");
        user.setLastName("Svetlana");
        user.setPhone("+380957960840");
        user.setEmail("otenko@gmail.com");

        final WorkingDay bookingDay = new WorkingDay();
        Timestamp start = Timestamp.valueOf(LocalDateTime.of(2023, 5, 1, startHour, 0));
        Timestamp finish = Timestamp.valueOf(LocalDateTime.of(2023, 5, 1, startHour + 1, 0));
        bookingDay.setWorkingDay(start.toLocalDateTime().toLocalDate());
        bookingDay.addWorkingTimePeriod(start.toLocalDateTime().toLocalTime(),
                finish.toLocalDateTime().toLocalTime());
        return new BookedService(salon, staff, user, bookingDay);
    }
}