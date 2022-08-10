package uz.jl.mapper;

import org.mapstruct.Mapper;
import uz.jl.domains.Product;
import uz.jl.dto.ProductDTO;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:01 PM 8/5/22 on Friday in August
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product fromProductDTO(ProductDTO productDTO);
}
