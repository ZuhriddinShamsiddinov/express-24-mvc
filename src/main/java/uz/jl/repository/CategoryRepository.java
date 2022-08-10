package uz.jl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jl.domains.Category;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 11:01 AM 8/4/22 on Thursday in August
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
