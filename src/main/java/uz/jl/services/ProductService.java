package uz.jl.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.jl.domains.details.Category;
import uz.jl.domains.Product;
import uz.jl.domains.Uploads;
import uz.jl.dto.ProductDTO;
import uz.jl.mapper.ProductMapper;
import uz.jl.repository.ProductRepository;
import uz.jl.services.details.CategoryService;

import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 12:15 PM 8/5/22 on Friday in August
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final FileStorageService fileStorageService;
    private final CategoryService categoryService;

    public void create(ProductDTO productDTO, long categoryId, MultipartFile file) {
        Product product = productMapper.fromProductDTO(productDTO);
        Uploads image = fileStorageService.upload(file);
        Category category = categoryService.findById(categoryId);
        product.setCategory(category);
        product.setImage(image);
        productRepository.save(product);
    }

    public List<Product> getAll(Long id) {
        return productRepository.findAllByCategoryId(id);
    }
}
