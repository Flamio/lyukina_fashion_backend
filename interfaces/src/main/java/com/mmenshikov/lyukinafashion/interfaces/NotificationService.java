package com.mmenshikov.lyukinafashion.interfaces;

public interface NotificationService {
    void send(String to, String body, String subject);
}
