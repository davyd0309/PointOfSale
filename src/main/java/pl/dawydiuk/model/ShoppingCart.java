package pl.dawydiuk.model;

import com.google.common.collect.Lists;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.dawydiuk.dto.ProductDto;

import java.util.List;

/**
 * Created by Konrad on 05.08.2017.
 */

@EqualsAndHashCode
@ToString
public class ShoppingCart {


    @Setter
    @Getter
    private List<ProductDto> addedProducts = Lists.newArrayList();

}
