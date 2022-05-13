package spdu2022.java.project.beutysalon.notification.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.exeptions.NotFoundException;
import spdu2022.java.project.beutysalon.notification.controllers.dto.UsersNotificationBySalonIdDTO;
import spdu2022.java.project.beutysalon.notification.models.Notification;
import spdu2022.java.project.beutysalon.notification.models.NotificationTypes;
import spdu2022.java.project.beutysalon.notification.services.creators_notifications.CreateNotificationsService;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UsersNotificationService {
    private final List<CreateNotificationsService> creatorsNotifications;
    private final List<NotificationServices> resources;

    public UsersNotificationService(List<CreateNotificationsService> creatorsNotifications, List<NotificationServices> resources) {
        this.resources = resources;
        this.creatorsNotifications = creatorsNotifications;
    }

    public void notificationUsersBySalonId(UsersNotificationBySalonIdDTO dto) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        final CreateNotificationsService creatorNotification = getCreatorByType(creatorsNotifications, dto.getTypeNotification());
        for(NotificationServices services : resources) {
            List<Notification> notifications = creatorNotification.createNotifications(dto);
            notifications.forEach(notification -> executorService.submit(new NotificationRunnable(services, notification)));
        }
        executorService.shutdown();
    }

    private CreateNotificationsService getCreatorByType(List<CreateNotificationsService> creatorsNotifications,
                                                        NotificationTypes notificationTypes) {
        return creatorsNotifications.stream().filter(x -> x.getNotificationsType().equals(notificationTypes)).findFirst()
                .orElseThrow(() -> new NotFoundException("Error in notification type"));
    }

}