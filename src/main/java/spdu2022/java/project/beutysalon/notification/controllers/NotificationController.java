package spdu2022.java.project.beutysalon.notification.controllers;

import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.entities.NotificationTypes;
import spdu2022.java.project.beutysalon.notification.controllers.dto.UsersNotificationBySalonIdDTO;
import spdu2022.java.project.beutysalon.notification.services.UsersNotificationService;

@RestController
@RequestMapping("api/v1/salons/{salonId}/users/notifications")
public class NotificationController {
    private final UsersNotificationService notificationServices;

    public NotificationController(UsersNotificationService notificationServices) {
        this.notificationServices = notificationServices;
    }

    @PostMapping()
    public String notificationUsersBySalonId(@RequestParam("type") NotificationTypes typeNotification, @RequestBody UsersNotificationBySalonIdDTO notificationDto, @PathVariable("salonId") long salonId) {
        notificationServices.notificationUsersBySalonId(typeNotification, notificationDto);
        return "Notification " + salonId;
    }
}
