package ma.easy_apps.boycott;

import ma.easy_apps.boycott.entities.AlternativeProduct;
import ma.easy_apps.boycott.entities.BoycottProduct;
import ma.easy_apps.boycott.entities.ProductCategory;
import ma.easy_apps.boycott.repos.AlternativeProductRepository;
import ma.easy_apps.boycott.repos.BoycottProductRepository;
import ma.easy_apps.boycott.repos.ProductCategoryRepository;
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
    public CommandLineRunner run(ProductCategoryRepository productCategoryRepository,
                    BoycottProductRepository boycottProductRepository,
                    AlternativeProductRepository alternativeProductRepository) {
        return args -> {
            // Initialize product categories
            if (productCategoryRepository.count() == 0) {
                productCategoryRepository.saveAll(List.of(
                        new ProductCategory("Food", "Food products"),
                        new ProductCategory("Electronics", "Electronic devices"),
                        new ProductCategory("Clothing", "Apparel and clothing items")
                ));
            }
            // Initialize alternative products
            if (alternativeProductRepository.count() == 0) {
                List<AlternativeProduct> alternativeProducts = alternativeProductRepository.saveAll(List.of(
                        new AlternativeProduct(true, "Alternative 1", "QR123", "http://example.com/alt1.jpg",null),
                        new AlternativeProduct(true, "Alternative 2", "QR456", "http://example.com/alt2.jpg",null),
                        new AlternativeProduct(true, "Alternative 3", "QR789", "http://example.com/alt3.jpg", null),
                        new AlternativeProduct(true, "Alternative 4", "QR101112", "http://example.com/alt4.jpg",null)
                ));
                // Initialize boycott products
                if (boycottProductRepository.count() == 0) {
                    List<ProductCategory> productCategories = productCategoryRepository.findAll();
                    boycottProductRepository.saveAll(List.of(
                            new BoycottProduct(true, "Boycott Product 1", "QR001", "http://example.com/boycott1.jpg", productCategories.get(0),alternativeProducts.stream().filter(ap -> ap.getQrCode().equals("QR123") || ap.getQrCode().equals("QR456")).toList()),
                            new BoycottProduct(true, "Boycott Product 2", "QR002", "http://example.com/boycott2.jpg", productCategories.get(1),alternativeProducts.stream().filter(ap -> ap.getQrCode().equals("QR789") || ap.getQrCode().equals("QR101112")).toList()),
                            new BoycottProduct(true, "Boycott Product 3", "QR003", "http://example.com/boycott3.jpg", productCategories.get(2),alternativeProducts.stream().filter(ap -> ap.getQrCode().equals("QR123") || ap.getQrCode().equals("QR456") || ap.getQrCode().equals("QR789")).toList()
                            ))
                    );
                }
            }
        };
    }
}
