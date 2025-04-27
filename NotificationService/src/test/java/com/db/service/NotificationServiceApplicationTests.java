package com.db.NotificationService;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NotificationServiceApplicationTests {

	 private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        notificationService = new NotificationService();
    }

    @Test
    void testSendNotification() {
        NotificationRequest request = new NotificationRequest();
        request.setToEmail("test@example.com");
        request.setSubject("Transaction Alert");
        request.setMessage("Your account has been credited with $500");

        String response = notificationService.sendNotification(request);

        assertTrue(response.contains("Notification sent successfully"));
        assertTrue(response.contains("test@example.com"));
    }
	
	@Test
	void contextLoads() {
	}

}
