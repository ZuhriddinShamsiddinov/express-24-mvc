package uz.jl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.jl.configs.security.UserDetails;
import uz.jl.domains.BasketItem;
import uz.jl.dto.BasketItemDTO;
import uz.jl.dto.CardDTO;
import uz.jl.services.BasketItemService;

import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 8:45 AM 8/8/22 on Monday in August
 */
@Controller
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketItemController {

    private final BasketItemService basketItemService;

    @GetMapping
    public String getAll(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<BasketItem> itemList = basketItemService.getAll(userDetails);
        model.addAttribute("itemList", itemList);
        double totalPrice = 0;
        for (BasketItem basketItem : itemList) {
            totalPrice += basketItem.getQuantity() * basketItem.getProduct().getPrice();
        }
        model.addAttribute("totalPrice", totalPrice);
        return "product/basket_products";
    }

    @PostMapping("/add/{id}")
    public String saveToBasket(@PathVariable("id") Long productId,
                               @ModelAttribute BasketItemDTO itemDTO,
                               @AuthenticationPrincipal UserDetails userDetails) {
        basketItemService.save(productId, itemDTO, userDetails);
        return "redirect:/";
    }

    @GetMapping("/payment")
    public String payment(Model model, @RequestParam("totalPrice") double totalPrice) {
        model.addAttribute("totalPrice", totalPrice);
        return "product/payment";
    }

    @PostMapping("/payment")
    @PreAuthorize("isAuthenticated()")
    public String checkout(@ModelAttribute CardDTO cardDTO, @AuthenticationPrincipal UserDetails userDetails, @RequestParam("totalAmount") double totalAmount) {
        basketItemService.checkout(cardDTO, userDetails, totalAmount);
        return "/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        basketItemService.delete(id, userDetails);
        return "redirect:/basket";
    }

}
