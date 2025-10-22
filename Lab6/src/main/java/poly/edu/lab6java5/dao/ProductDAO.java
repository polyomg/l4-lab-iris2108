package poly.edu.lab6java5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.edu.lab6java5.entity.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
}
