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
public class ProductServiceImpl implements ProductService{


    private ProductDao productDao;


    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    public  Collection<Product> findAll(){
      return  productDao.findAll();
    }
    public ProductDto findProductByBarcode(String barcode){
        return productDao.findProductByBarcode(barcode);
    }


}
