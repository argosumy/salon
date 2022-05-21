package spdu2022.java.project.beutysalon.notification.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.exeptions.NotFoundException;
import spdu2022.java.project.beutysalon.notification.controllers.dto.UsersNotificationBySalonIdDTO;
import spdu2022.java.project.beutysalon.notification.models.Counter;
import spdu2022.java.project.beutysalon.notification.models.Notification;
import spdu2022.java.project.beutysalon.notification.models.NotificationType;
import spdu2022.java.project.beutysalon.notification.services.creators_notifications.CreateNotificationsService;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

@Service
public class UsersNotificationService {
    private final List<CreateNotificationsService> creatorsNotifications;
    private final List<NotificationService> resources;
    private final ExecutorService executorService;

    public UsersNotificationService(List<CreateNotificationsService> creatorsNotifications, List<NotificationService> resources) {
        this.resources = resources;
        this.creatorsNotifications = creatorsNotifications;
        this.executorService = Executors.newFixedThreadPool(4);
    }

    public int sendingNotificationToUsersBySalonId(UsersNotificationBySalonIdDTO dto) {
        final Counter counter = new Counter();//<--BEST PRACTICE
        final List<Future<Boolean>> futures = new ArrayList<>();
        final CreateNotificationsService creatorNotification = getCreatorByType(creatorsNotifications, dto.getTypeNotification());
        for(NotificationService services : resources) {
            Set<Notification> notifications = creatorNotification.createNotifications(dto);
            notifications.forEach(notification -> {
                Future<Boolean> future = executorService.submit(() -> {
                    services.send(notification);
                    counter.incrementCount();
                    return true;
                });
                futures.add(future);
            });
        }
        threadWaitUntilFuturesComplete(futures);
        return counter.getCount();
    }

    private CreateNotificationsService getCreatorByType(List<CreateNotificationsService> creatorsNotifications,
                                                        NotificationType notificationType) {
        return creatorsNotifications.stream()
                .filter(x -> x.getNotificationsType().equals(notificationType))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Does not correct type - " + notificationType));
    }

    private void threadWaitUntilFuturesComplete(List<Future<Boolean>> futures) {
        for (Future<Boolean> future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {       //<-ATTENTION
                Thread.currentThread().interrupt(); //<-ATTENTION
            } catch (ExecutionException e) {
                throw new RuntimeException("Failed execution ...", e);
            }
        }
    }

    @PreDestroy
    private void shutDownOfResource() throws InterruptedException{
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }
}