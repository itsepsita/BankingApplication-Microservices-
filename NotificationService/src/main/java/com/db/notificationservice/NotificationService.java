package com.db.NotificationService;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public String sendNotification(NotificationRequest notificationRequest) {
        // Simulate sending email
        System.out.println("Sending email to: " + notificationRequest.getToEmail());
        System.out.println("Subject: " + notificationRequest.getSubject());
        System.out.println("Message: " + notificationRequest.getMessage());
        return "Notification sent successfully to " + notificationRequest.getToEmail();
    }
}
