package com.mmenshikov.lyukinafashion.notification.service;

import com.mmenshikov.lyukinafashion.interfaces.NotificationService;
import com.mmenshikov.lyukinafashion.notification.properties.NotificationProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender javaMailSender;
    private final NotificationProperties notificationConfig;

    @SneakyThrows
    @Override
    public void send(final String to,
                     final String body,
                     final String subject) {
        var message = javaMailSender.createMimeMessage();
        var helper = new MimeMessageHelper(message, "utf-8");

        helper.setFrom(notificationConfig.getEmailFrom());
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);
        javaMailSender.send(message);
    }
}
