package spdu2022.java.project.beutysalon.notification.services.creators_notifications;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.BookedService;
import spdu2022.java.project.beutysalon.notification.controllers.dto.UsersNotificationBySalonIdDTO;
import spdu2022.java.project.beutysalon.notification.models.Notification;
import spdu2022.java.project.beutysalon.notification.models.NotificationType;
import spdu2022.java.project.beutysalon.notification.persistence.reposetories.GetBookingServiceRepository;
import spdu2022.java.project.beutysalon.notification.services.NotificationMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CreateCancelingNotifications implements CreateNotificationsService {
    private final GetBookingServiceRepository bookingServiceRepository;
    private final NotificationMapper mapper;

    public CreateCancelingNotifications(GetBookingServiceRepository bookingServiceRepository, NotificationMapper mapper) {
        this.bookingServiceRepository = bookingServiceRepository;
        this.mapper = mapper;
    }

    @Override
    public Set<Notification> createNotifications(UsersNotificationBySalonIdDTO dto) {
        List<BookedService> bookedServices = bookingServiceRepository.getBookingBySalonId(dto.getSalonId());
        return new HashSet<>(mapper.mapToNotifications(bookedServices, dto));
    }

    @Override
    public NotificationType getNotificationsType() {
        return NotificationType.CANCELING_BOOKING;
    }
}
