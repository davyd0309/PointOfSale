package pl.dawydiuk.dao;

import org.springframework.jdbc.core.RowMapper;
import pl.dawydiuk.dto.ProductDto;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Konrad on 05.08.2017.
 */
public class ProductMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        ProductDto productDto = new ProductDto();
        productDto.setName(resultSet.getString("NAME"));
        productDto.setPrice(resultSet.getBigDecimal("PRICE"));
        productDto.setBarCode(resultSet.getString("BARCODE"));
        return productDto;
    }
}
