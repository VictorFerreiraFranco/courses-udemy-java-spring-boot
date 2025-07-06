package udemy.courses.productapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udemy.courses.productapi.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByName(String name);

}
