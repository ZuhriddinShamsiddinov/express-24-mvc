package uz.jl.services.details;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.configs.security.UserDetails;
import uz.jl.domains.auth.AuthUser;
import uz.jl.domains.details.BasketItem;
import uz.jl.domains.details.Card;
import uz.jl.domains.Product;
import uz.jl.dto.details.BasketItemDTO;
import uz.jl.dto.details.CardDTO;
import uz.jl.exceptions.NotfoundException;
import uz.jl.mapper.CardMapper;
import uz.jl.repository.details.BasketItemRepository;
import uz.jl.repository.details.CardRepository;
import uz.jl.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 8:50 AM 8/8/22 on Monday in August
 */
@Service
@RequiredArgsConstructor
public class BasketItemService {
    private final ProductRepository productRepository;
    private final BasketItemRepository basketItemRepository;
    private final CardMapper cardMapper;
    private final CardRepository cardRepository;

    public void save(long productId, BasketItemDTO itemDTO, UserDetails userDetails) {
        AuthUser user = userDetails.getUser();
        Product product = productRepository.findById(productId).get();
        Optional<BasketItem> itemOptional = basketItemRepository.findBasketItemByAuthUserIdAndProductId(user.getId(), productId);
        if (itemOptional.isPresent()) {
            BasketItem basketItem = itemOptional.get();
            basketItem.setQuantity(basketItem.getQuantity() + itemDTO.getQuantity());
            basketItemRepository.save(basketItem);
        } else {
            BasketItem basketItem = new BasketItem();
            basketItem.setProduct(product);
            basketItem.setAuthUser(user);
            basketItem.setQuantity(itemDTO.getQuantity());
            basketItemRepository.save(basketItem);
        }
    }


    public List<BasketItem> getAll(UserDetails userDetails) {
        AuthUser user = userDetails.getUser();
        return basketItemRepository.findAllByUserIdAndConfirmedFalse(user.getId());
    }


    public void delete(Long id, UserDetails userDetails) {
        Optional<BasketItem> basketItemOptional = basketItemRepository.findBasketItemByIdAndAuthUserId(id, userDetails.getUser().getId());
        if (basketItemOptional.isPresent()) {
            BasketItem basketItem = basketItemOptional.get();
            basketItemRepository.delete(basketItem);
        }
    }

    public void checkout(CardDTO cardDTO, UserDetails userDetails, double totalAmount) {
//        Card card = cardMapper.fromCardDTO(cardDTO);
        Optional<Card> cardOptional = cardRepository.findByCode(cardDTO.getCode());
        if (cardOptional.isPresent()) {
            Card card = cardOptional.get();
            if (!card.getPassword().equals(cardDTO.getPassword())) {
                throw new RuntimeException("Bad credentials");
            }
            List<BasketItem> basketItemList = getAll(userDetails);
            basketItemList.forEach(basketItem -> basketItem.setConfirmed(true));
            card.setAmount(card.getAmount() - totalAmount);
            cardRepository.save(card);
            basketItemRepository.saveAll(basketItemList);
        } else {
            throw new NotfoundException("Card not found");
        }
    }
}
