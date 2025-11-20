package com.example.scheduleproject.Service;


import com.example.scheduleproject.Repository.LogEmailRepository;
import com.example.scheduleproject.model.LogEmail;
import com.example.scheduleproject.model.LogMailBuilder;
import com.example.scheduleproject.model.Product;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final LogEmailRepository logEmailRepository;

    public EmailService(JavaMailSender mailSender, LogEmailRepository logEmailRepository) {
        this.mailSender = mailSender;
        this.logEmailRepository = logEmailRepository;
    }
public void sendProductsToUser(String to,String htmlContent, List<Product> products) {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
    try {
        helper.setTo(to);
        helper.setSubject("product list");
        helper.setText(htmlContent, true);
        helper.setFrom("sinaerfani66@gmail.com");

        mailSender.send(message);
        for (Product p : products) {
            LogEmail log = LogMailBuilder.builder()
                    .setProductName(p.getName())
                    .setUserEmail(to)
                    .setStatus("SUCCESS")
                    .build();

            logEmailRepository.save(log);
        }

        }catch(Exception e){
            for (Product p : products) {
                LogEmail log = LogMailBuilder.builder()
                        .setProductName(p.getName())
                        .setUserEmail(to)
                        .setStatus("FAILED")
                        .build();

                logEmailRepository.save(log);

            }
        }
    }


}













