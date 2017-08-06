package pl.dawydiuk.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created by kdawydiuk on 2017-08-04.
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Product {

    @Setter
    @Getter
    private Integer id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private BigDecimal price;

    @Setter
    @Getter
    private String barcode;


    public Product() {
    }
}
