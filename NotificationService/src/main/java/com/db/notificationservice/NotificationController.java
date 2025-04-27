package com.db.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public String sendNotification(@RequestBody NotificationRequest notificationRequest) {
        return notificationService.sendNotification(notificationRequest);
    }
}