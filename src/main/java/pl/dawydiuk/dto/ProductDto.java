package pl.dawydiuk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Konrad on 05.08.2017.
 */

@AllArgsConstructor
public class ProductDto {


    @Setter
    @Getter
    private String barCode;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private BigDecimal price;

    public ProductDto() {
    }
}
