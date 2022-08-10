package uz.jl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.jl.domains.Product;
import uz.jl.dto.ProductDTO;
import uz.jl.services.CategoryService;
import uz.jl.services.ProductService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 12:16 PM 8/5/22 on Friday in August
 */
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("category/{id}")
    public String getProducts(@PathVariable Long id, Model model) {
        List<Product> productList = productService.getAll(id);
        model.addAttribute("productList", productList);
        model.addAttribute("category", categoryService.findById(id));
        return "product/product";
    }

    @PostMapping("/add")
    public String saveProduct(@ModelAttribute ProductDTO productDTO, @RequestParam("categoryId") Long id, @RequestParam("image") MultipartFile file) {
        productService.create(productDTO, id, file);
        return "redirect:/product/" + id;
    }

    @GetMapping("/display")
    public void getImage(@RequestParam("img") String requestedImage, HttpServletResponse response) throws IOException {
        Path path = Paths.get("/home/zuhriddin/IdeaProjects/spring/spring-mvc/spring-security-mvc/src/main/resources/", requestedImage);
        ServletOutputStream outputStream = response.getOutputStream();
        Files.copy(path, outputStream);
    }
}
