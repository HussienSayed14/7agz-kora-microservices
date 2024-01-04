package com.accountmicroservice.util;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {


    private final JavaMailSender mailSender;

    @Async
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending Email");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        try {

            mailSender.send(message);
            System.out.println("Mail will be Sent");
        } catch (Exception e) {
            System.out.println("Error Sending Email");
            System.out.println(e.getMessage());

        }

    }

    public static String generateOTP(int length) {
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[length];
        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return new String(otp);
    }


}

