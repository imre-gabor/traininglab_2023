package webshop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import webshop.model.Category;
import webshop.model.Product;
import webshop.repository.CategoryRepository;
import webshop.repository.ProductRepository;

@RequiredArgsConstructor
@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;
	
	private final ProductRepository productRepository;
	

//	@Transactional
	public void discountProductsInCategory(String name, int percent) {
		
		List<Category> categories = categoryRepository.findByName(name);
		
		categories.forEach(c -> {
			c.getProducts().forEach( p -> {
				discountProduct(p, percent);
				productRepository.save(p); //save not needed, because of Transactional
			});
		});
	}

	private void discountProduct(Product product, int percent) {
		product.setPrice(product.getPrice() * (100.0 - percent) / 100.0);
	}
	
}
