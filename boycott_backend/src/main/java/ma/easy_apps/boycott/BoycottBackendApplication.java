package ma.easy_apps.boycott;


import ma.easy_apps.boycott.dto.input.CreateCategoryInput;
import ma.easy_apps.boycott.dto.input.CreateProductInput;
import ma.easy_apps.boycott.services.ProductCategoryService;
import ma.easy_apps.boycott.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class BoycottBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoycottBackendApplication.class, args);
    }
    @Bean
    CommandLineRunner initData(ProductService productService, ProductCategoryService productCategoryService) {
        return args -> {
            //create some categories
            List.of("Electronics", "Clothing", "Food", "Health & Beauty", "Home & Garden").forEach(categoryName -> {
                CreateCategoryInput input = new CreateCategoryInput();
                input.setName(categoryName);
                productCategoryService.createCategory(input);
            });
            //create some products
            productService.createProduct(CreateProductInput.builder()
                    .name("Smartphone")
                    .qrCode("qr-smartphone")
                    .categoryName("Electronics")
                    .imageUrl("cae184ab_20250803_140331.jpg")
                    .isBoycott(false)
                    .build());
            productService.createProduct(CreateProductInput.builder()
                    .name("T-Shirt")
                    .qrCode("qr-tshirt")
                    .categoryName("Clothing")
                    .imageUrl("cae184ab_20250803_140331.jpg")
                    .isBoycott(true)
                    .build());
            productService.createProduct(CreateProductInput.builder()
                    .name("Organic Soap")
                    .qrCode("qr-soap")
                    .categoryName("Health & Beauty")
                    .imageUrl("cae184ab_20250803_140331.jpg")
                    .isBoycott(false)
                    .build());
            productService.createProduct(CreateProductInput.builder()
                    .name("Garden Tools Set")
                    .qrCode("qr-garden-tools")
                    .categoryName("Home & Garden")
                    .imageUrl("cae184ab_20250803_140331.jpg")
                    .isBoycott(true)
                    .build());
            productService.createProduct(CreateProductInput.builder()
                    .name("Organic Apple Juice")
                    .qrCode("qr-apple-juice")
                    .categoryName("Food")
                    .imageUrl("cae184ab_20250803_140331.jpg")
                    .isBoycott(false)
                    .build());

        };
    }
}
