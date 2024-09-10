package com.jsp.ets.utility;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MailSenderService {

    private JavaMailSender mailSender;
    private static final Logger logger = LoggerFactory.getLogger(MailSenderService.class);

    @Async
    public void sendMail(MessageModel messageModel) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;

        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(messageModel.getTo());
            helper.setText(messageModel.getText(), true);
            helper.setSubject(messageModel.getSubject());
            helper.setSentDate(messageModel.getSentDate());

            // Log the email details
            logger.info("Sending email to: {}", messageModel.getTo());
            logger.info("Subject: {}", messageModel.getSubject());
            logger.info("Sent date: {}", messageModel.getSentDate());

        } catch (MessagingException e) {
            logger.error("Error sending email", e);
            throw new RuntimeException(e);
        }

        mailSender.send(mimeMessage);
        logger.info("Email successfully sent to: {}", messageModel.getTo());
    }

}
