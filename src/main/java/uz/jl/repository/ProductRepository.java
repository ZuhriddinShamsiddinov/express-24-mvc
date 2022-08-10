package uz.jl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jl.domains.Product;

import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 12:15 PM 8/5/22 on Friday in August
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(Long id);
}
