package spdu2022.java.project.beutysalon.notification.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.BookedService;
import spdu2022.java.project.beutysalon.notification.controllers.dto.UsersNotificationBySalonIdDTO;
import spdu2022.java.project.beutysalon.notification.models.Notification;
import spdu2022.java.project.beutysalon.notification.persistence.reposetories.GetBookingServiceRepository;

import java.util.List;
import java.util.Set;

@Service
public class UsersNotificationService {
    private final GetBookingServiceRepository getBookingRepository;
    private final List<NotificationServices> resources;

    public UsersNotificationService(GetBookingServiceRepository getBookingRepository,
                                    List<NotificationServices> resources) {
        this.getBookingRepository = getBookingRepository;
        this.resources = resources;
    }

    public void notificationUsersBySalonId(UsersNotificationBySalonIdDTO dto) {
        final Set<BookedService> bookedServices = Set.copyOf(getBookingRepository.getBookingBySalonId(dto.getSalonId()));
        for(NotificationServices services : resources) {
            bookedServices.forEach(x -> {
                Notification notification = NotificationMapper.mapBookedService(x, dto);
                services.usersNotification(notification);
            });
        }
    }

}
