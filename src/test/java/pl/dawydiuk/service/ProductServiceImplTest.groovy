package pl.dawydiuk.service

import pl.dawydiuk.dao.ProductDao
import pl.dawydiuk.dao.ProductDaoImpl
import pl.dawydiuk.dto.ProductDto
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by Konrad on 06.08.2017.
 */
class ProductServiceImplTest extends Specification {


    @Shared
    ProductDao productDao

    @Shared
    ProductService productService


    def setupSpec() {
        productDao = Stub(ProductDaoImpl.class)
        productService = new ProductServiceImpl(productDao)
    }


    @Ignore
    def "should method start in dao - 'findAll'"() {
        when:
        productService.findAll()
        then:
        1 * productDao.findAll()

    }


    @Ignore
    @Unroll
    def "should method for #barcode start in dao-'findProductByBarcode' "() {
        when:
        productService.findProductByBarcode(barcode)
        then:
        1 * productDao.findProductByBarcode(barcode)
        where:
        barcode << ["Prod_98756", "Prod_78412"]
    }

    @Unroll
    def "should #productDto add to shopping cart - 'addProductToShoppingCart'"() {
        when:
        productService.addProductToShoppingCart(productDto)
        then:
        productService.getAddedProducts().size() == size
        where:
        productDto                                                      | size
        new ProductDto("Prod_78412", "Telefon", new BigDecimal(235))    | 1
        null                                                            | 1
        new ProductDto("Prod_98765", "Klawiatura", new BigDecimal(100)) | 2

    }


    def "should shopping cart be empty - 'clearAllScannedProduct'"() {
        given:
        List<ProductDto> products = productService.getAddedProducts()
        when:
        products.add(new ProductDto("Prod_78412", "Telefon", new BigDecimal(235)))
        products.add(new ProductDto("Prod_98765", "Klawiatura", new BigDecimal(100)))
        then:
        productService.getAddedProducts().size() == 2
        when:
        productService.clearAllScannedProduct();
        then:
        products.isEmpty()

    }


}
