package com.example.scheduleproject.controller;

import com.example.scheduleproject.Service.ProductService;
import com.example.scheduleproject.model.Product;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestParam String name,
                                  @RequestParam("image") MultipartFile file) {
        try {
            Product product = new Product();
            product.setName(name);
            product.setType(file.getContentType());

            // اگر فایل خالی بود
            if (!file.isEmpty()) {
                product.setImage(file.getBytes());
            } else {
                return ResponseEntity.badRequest().body("تصویر محصول ارسال نشده است");
            }

            productService.saveProduct(product);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("خطا در ذخیره محصول");
        }
    }
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            if (product == null || product.getImage() == null) {
                return ResponseEntity.notFound().build();
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(product.getType()));
            headers.setContentDispositionFormData("attachment", product.getName() + ".jpg");

            return new ResponseEntity<>(product.getImage(), headers, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

