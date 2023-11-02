package webshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import webshop.model.Category;
import webshop.model.Product;
import webshop.repository.CategoryRepository;
import webshop.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class InitDbService {

	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	private final CategoryService categoryService;
	
	@Transactional
	public void initDb() {
//		Product prod1 = productRepository.save(new Product(0, "Prod1", 100.0, null));
//		
//		Product prod2 = productRepository.save(new Product(0, "Prod2", 100.0, null));
//		
//		Product prod3 = productRepository.save(new Product(0, "Prod3", 100.0, null));
//		
//		Category cat1 = categoryRepository.save(new Category(0, "cat1", null));
//		cat1.addProduct(prod1);
//		cat1.addProduct(prod2);
//		
//		Category cat2 = categoryRepository.save(new Category(0, "cat2", null));
//		cat2.addProduct(prod3);
		
		categoryService.discountProductsInCategory("cat1", 10);
	}
}
