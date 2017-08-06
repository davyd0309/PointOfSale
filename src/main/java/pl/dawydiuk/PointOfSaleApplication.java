package pl.dawydiuk;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.dawydiuk.dao.ProductDao;

@SpringBootApplication
public class PointOfSaleApplication implements CommandLineRunner {

	@Autowired
	private ProductDao dao;

	public static void main(String[] args) {
		SpringApplication.run(PointOfSaleApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		dao.dropCreateTable();
		dao.insertInToTable();
	}
}
