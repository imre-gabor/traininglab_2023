package webshop.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import webshop.model.Category;
import webshop.model.Product;
import webshop.repository.CategoryRepository;
import webshop.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

	@InjectMocks
	CategoryService categoryService;
	
	@Mock
	CategoryRepository categoryRepository;
	
	@Mock
	ProductRepository productRepository;
	
	
	@Test
	void testDiscountProductsInCategory() throws Exception {
		
		//ARRANGE
		Category testcat = new Category(1, "testcat", 
				Arrays.asList(
						new Product(1, "testprod1", 100.0, null),
						new Product(2, "testprod2", 200.0, null)
					)
		);
		Mockito.when(categoryRepository.findByName("testcat"))
			.thenReturn(Arrays.asList(testcat));
		
		Mockito.when(productRepository.save(Mockito.any()))
			//.thenReturn(null); in this case, we don't use the return value
			.thenAnswer(inv -> inv.getArguments()[0]);
		
		//ACT
		categoryService.discountProductsInCategory("testcat", 10);
		
		//ASSERT
		assertEquals(90.0, testcat.getProducts().get(0).getPrice());
		assertEquals(180.0, testcat.getProducts().get(1).getPrice());
		Mockito.verify(productRepository, times(2)).save(Mockito.any());
		
	}
}
