package uz.jl.mapper;

import org.mapstruct.Mapper;
import uz.jl.domains.Card;
import uz.jl.dto.CardDTO;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 9:41 AM 8/10/22 on Wednesday in August
 */
@Mapper(componentModel = "spring")
public interface CardMapper {
    Card fromCardDTO(CardDTO cardDTO);
}
