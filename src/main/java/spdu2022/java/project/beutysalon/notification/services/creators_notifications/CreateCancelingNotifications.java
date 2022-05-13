package spdu2022.java.project.beutysalon.notification.services.creators_notifications;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.BookedService;
import spdu2022.java.project.beutysalon.notification.controllers.dto.UsersNotificationBySalonIdDTO;
import spdu2022.java.project.beutysalon.notification.models.Notification;
import spdu2022.java.project.beutysalon.notification.models.NotificationTypes;
import spdu2022.java.project.beutysalon.notification.persistence.reposetories.GetBookingServiceRepository;
import spdu2022.java.project.beutysalon.notification.services.NotificationMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateCancelingNotifications implements CreateNotificationsService {
    private final GetBookingServiceRepository bookingServiceRepository;

    public CreateCancelingNotifications(GetBookingServiceRepository bookingServiceRepository) {
        this.bookingServiceRepository = bookingServiceRepository;
    }

    @Override
    public List<Notification> createNotifications(UsersNotificationBySalonIdDTO dto) {
        List<BookedService> bookedServices = bookingServiceRepository.getBookingBySalonId(dto.getSalonId());
        return new NotificationMapper().mapToNotifications(bookedServices, dto)
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public NotificationTypes getNotificationsType() {
        return NotificationTypes.CANCELING_BOOKING;
    }
}
