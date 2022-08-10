package uz.jl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.jl.dto.CategoryDTO;
import uz.jl.services.CategoryService;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 2:23 PM 8/4/22 on Thursday in August
 */
@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String save(@ModelAttribute CategoryDTO categoryDTO, @RequestParam("image") MultipartFile file) {
        categoryService.create(categoryDTO, file);
        return "redirect:/";
    }

    @GetMapping("/display")
    public void getImage(@RequestParam("img") String requestedImage, HttpServletResponse response) throws IOException {
        Path path = Paths.get("/home/zuhriddin/IdeaProjects/spring/spring-mvc/spring-security-mvc/src/main/resources/", requestedImage);
        ServletOutputStream outputStream = response.getOutputStream();
        Files.copy(path, outputStream);
    }
}
