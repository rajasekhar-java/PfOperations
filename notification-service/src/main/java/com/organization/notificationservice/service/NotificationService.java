package com.organization.notificationservice.service;

import com.organization.notificationservice.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private JavaMailSender mailSender;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiveMessage(String mailContent) {
        System.out.println("📢 Notification Received: " + mailContent);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rajasekharpk7java@gmail.com");
        message.setTo("pippallarajasekhar@gmail.com");
        message.setSubject("PF Contribution");
        message.setText(mailContent);
        mailSender.send(message);
        // Extend this: send email, push, etc.
    }
}
