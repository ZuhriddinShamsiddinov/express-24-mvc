package uz.jl.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.jl.services.CategoryService;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;

    @RequestMapping
    @PreAuthorize("permitAll()")
    public String homePage(Model model) {
        model.addAttribute("categoryList", categoryService.getAll());
        return "index";
    }
}
