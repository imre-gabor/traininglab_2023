package webshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import webshop.model.Category;
import webshop.model.Product;
import webshop.repository.CategoryRepository;
import webshop.repository.ProductRepository;

@SpringBootTest
@AutoConfigureTestDatabase
public class CategoryServiceIT {

	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	
	@Test
	void testDiscountProductsInCategory() throws Exception {
		
		//ARRANGE
		Product prod1 = productRepository.save(new Product(0, "Prod1", 100.0, null));
		
		Product prod2 = productRepository.save(new Product(0, "Prod2", 200.0, null));
		
		Product prod3 = productRepository.save(new Product(0, "Prod3", 100.0, null));
		
		Category cat1 = categoryRepository.save(new Category(0, "cat1", null));
		cat1.addProduct(prod1);
		cat1.addProduct(prod2);
		productRepository.saveAll(Arrays.asList(prod1, prod2));
		
		Category cat2 = categoryRepository.save(new Category(0, "cat2", null));
		cat2.addProduct(prod3);
		productRepository.save(prod3);
		
		//ACT
		categoryService.discountProductsInCategory("cat1", 10);
		
		//ASSERT
		List<Product> productsFromDb = productRepository.findAll();
		productsFromDb.sort(Comparator.comparing(Product::getId));
		assertEquals(90.0, productsFromDb.get(0).getPrice());
		assertEquals(180.0, productsFromDb.get(1).getPrice());
		assertEquals(100.0, productsFromDb.get(2).getPrice());
		
	}
}
