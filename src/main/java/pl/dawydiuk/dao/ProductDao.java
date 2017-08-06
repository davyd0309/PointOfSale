package pl.dawydiuk.dao;

import pl.dawydiuk.dto.ProductDto;
import pl.dawydiuk.model.Product;

import java.util.Collection;

/**
 * Created by Konrad on 05.08.2017.
 */
public interface ProductDao {

    void dropCreateTable();
    void insertInToTable();
    Collection<Product> findAll();

    ProductDto findProductByBarcode(String barcode);
}
