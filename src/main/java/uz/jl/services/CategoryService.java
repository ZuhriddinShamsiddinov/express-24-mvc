package uz.jl.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.jl.domains.Category;
import uz.jl.domains.Uploads;
import uz.jl.dto.CategoryDTO;
import uz.jl.exceptions.NotfoundException;
import uz.jl.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 11:01 AM 8/4/22 on Thursday in August
 */
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final FileStorageService fileStorageService;
    private final CategoryRepository categoryRepository;

    public void create(CategoryDTO categoryDTO, MultipartFile file) {
        Uploads image = fileStorageService.upload(file);
        Category category = Category
                .builder()
                .name(categoryDTO.getName())
                .image(image)
                .build();
        categoryRepository.save(category);
    }

    public List<Category> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) {
            return new ArrayList<>();
        }
        return categoryList;
    }

    public Category findById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new NotfoundException("Category not found");
        }
        return categoryOptional.get();
    }
}