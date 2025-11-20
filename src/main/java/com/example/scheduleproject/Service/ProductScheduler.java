package com.example.scheduleproject.Service;

import com.example.scheduleproject.Repository.ProductRepository;
import com.example.scheduleproject.Repository.UserRepository;
import com.example.scheduleproject.model.Product;
import com.example.scheduleproject.model.Users;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductScheduler {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final HtmlBuilderService htmlBuilderService;
    private final EmailService emailService;

    public ProductScheduler(ProductRepository productRepository, UserRepository userRepository, HtmlBuilderService htmlBuilderService, EmailService emailService) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.htmlBuilderService = htmlBuilderService;
        this.emailService = emailService;
    }
   @Scheduled(fixedRate = 5000)
    public void sendProducts(){
        List<Users> users = userRepository.findAll();
        List<Product> products = productRepository.findAll();
        if (products.isEmpty() || users.isEmpty()) return;
        String html = htmlBuilderService.buildProductHtml(products);
        for (Users user : users) {
            emailService.sendProductsToUser(user.getEmail(), html, products);
        }
    }
}
