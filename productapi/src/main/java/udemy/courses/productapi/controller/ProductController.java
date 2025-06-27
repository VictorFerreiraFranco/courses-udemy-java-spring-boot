package udemy.courses.productapi.controller;

import org.springframework.web.bind.annotation.*;
import udemy.courses.productapi.model.Product;
import udemy.courses.productapi.repository.ProductRepository;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        System.out.println("Product: " + product);

        product.setId(UUID.randomUUID().toString());
        productRepository.save(product);

        return product;
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") String id) {
//        Optional<Product> product = productRepository.findById(id);
//        return product.isPresent() ? product.get() : null;
        return productRepository.findById(id).orElse(null);
    }
}
