package pl.dawydiuk.dao;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pl.dawydiuk.dto.ProductDto;
import pl.dawydiuk.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Konrad on 05.08.2017.
 */
@Repository
@Log
public class ProductDaoImpl implements ProductDao {



    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void dropCreateTable() {
        log.info("Creat table");
        jdbcTemplate.execute("DROP TABLE PRODUCTS IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE PRODUCTS(" +
                "ID INT,NAME VARCHAR(100),BARCODE VARCHAR(20),PRICE DECIMAL )");
    }

    public void insertInToTable() {

        log.info("Insert into table");
        jdbcTemplate.batchUpdate("INSERT INTO PRODUCTS (ID,NAME,PRICE,BARCODE) " +
                "VALUES(1,'Telefon',100,'Prod_56154')," +
                "(2,'Telewizor',299,'Prod_12658')," +
                "(3,'Pralka',450,'Prod_45896')," +
                "(4,'Kalkulator',99,'Prod_36578')," +
                "(5,'Drukarka',150,'Prod_98756')," +
                "(6,'Komputer',800,'Prod_10654')," +
                "(7,'Monitor',340,'Prod_78412')");
    }


    @Override
    public Collection<Product> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM PRODUCTS", new BeanPropertyRowMapper<Product>(Product.class));
    }

    @Override
    public ProductDto findProductByBarcode(String barcode) {
        List query = this.jdbcTemplate.query("SELECT * FROM PRODUCTS WHERE BARCODE = ?", new Object[]{barcode}, new ProductMapper());
        if (query.size() == 1) {
            return (ProductDto) query.get(0);
        } else {
            return null;
        }

    }


}
