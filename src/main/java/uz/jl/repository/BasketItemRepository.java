package uz.jl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.jl.domains.BasketItem;

import java.util.List;
import java.util.Optional;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 8:45 AM 8/8/22 on Monday in August
 */
public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
    Optional<BasketItem> findBasketItemByAuthUserIdAndProductId(Long authUserId, Long productId);

    //    void deleteByAuthUserIdAndId(Long userId, Long basketId);
    Optional<BasketItem> findBasketItemByIdAndAuthUserId(Long id, Long userId);

    @Query("from BasketItem as t where t.authUser.id=:id and t.isConfirmed=false")
    List<BasketItem> findAllByUserIdAndConfirmedFalse(@Param("id") Long userId);
}
