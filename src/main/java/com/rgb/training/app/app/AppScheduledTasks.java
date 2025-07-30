package com.rgb.training.app.app;

import jakarta.ejb.Schedule;
import jakarta.ejb.Schedules;
import java.time.LocalDateTime;

/**
 *
 * @author luiscarlosgonzalez
 */
//@Startup
//@Singleton
public class AppScheduledTasks {

    @Schedule(second = "*/5", minute = "*", hour = "*")
    public void automaticTimer() {
        System.out.println("Cada 5 segundos:" + LocalDateTime.now());
    }
    
    @Schedules({
        @Schedule(hour = "12"),
        @Schedule(hour = "20")
    })
    public void multipleSchedule() {
        System.out.println("Multiple segundos:" + LocalDateTime.now());
    }
}
