package pl.dawydiuk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dawydiuk.dao.ProductDao;
import pl.dawydiuk.dto.ProductDto;
import pl.dawydiuk.model.Product;

import java.util.Collection;
import java.util.List;

/**
 * Created by kdawydiuk on 2017-08-04.
 */

@Service
public interface ProductService {

    Collection<Product> findAll();

    ProductDto findProductByBarcode(String barcode);

}
