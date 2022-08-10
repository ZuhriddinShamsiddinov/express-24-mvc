package uz.jl.repository.details;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jl.domains.details.Card;

import java.util.Optional;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 10:19 AM 8/10/22 on Wednesday in August
 */
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByCode(String code);
}
