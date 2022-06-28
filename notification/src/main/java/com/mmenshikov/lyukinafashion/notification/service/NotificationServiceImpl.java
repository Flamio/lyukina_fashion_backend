package com.mmenshikov.lyukinafashion.notification.service;

import com.mmenshikov.lyukinafashion.interfaces.NotificationService;
import com.mmenshikov.lyukinafashion.notification.properties.NotificationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender javaMailSender;
    private final NotificationProperties notificationConfig;

    @Override
    public void send(final String to,
                     final String body,
                     final String subject) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(notificationConfig.getEmailFrom());
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }
}
