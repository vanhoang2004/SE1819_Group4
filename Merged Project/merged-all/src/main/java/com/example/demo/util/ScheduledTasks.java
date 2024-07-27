package com.example.demo.util;

import com.example.demo.data.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    private NotificationRepository nr;

    @Scheduled(cron = "0 0 0 1 7 ?") // Runs at midnight on the first day of July
    public void scheduleNotificationsDeletion() {
        nr.deleteAll();
    }

}
