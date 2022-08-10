package uz.jl.dto;

import lombok.*;

import javax.persistence.Column;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:03 PM 8/5/22 on Friday in August
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private String name;
    private String description;
    private double price;
}
