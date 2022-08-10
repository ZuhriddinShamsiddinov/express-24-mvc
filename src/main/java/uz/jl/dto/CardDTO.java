package uz.jl.dto;

import lombok.*;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 9:33 AM 8/10/22 on Wednesday in August
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDTO {
    private String code;
    private String password;
}
