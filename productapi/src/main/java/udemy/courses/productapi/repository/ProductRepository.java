package udemy.courses.productapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udemy.courses.productapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
