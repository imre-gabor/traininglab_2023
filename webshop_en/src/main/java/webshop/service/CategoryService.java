package webshop.service;

import java.util.List;

import webshop.model.Category;
import webshop.model.Product;
import webshop.repository.CategoryRepository;
import webshop.repository.ProductRepository;

public class CategoryService {

	private CategoryRepository categoryRepository;
	private ProductRepository productRepository;
	
	public void discountProductsInCategory(String name, int percent) {
		
		List<Category> categories = categoryRepository.findByName(name);
		
		categories.forEach(c ->{
			c.getProducts().forEach( p -> {
				discountProduct(p, percent);
				productRepository.save(p);
			});
		});
	}

	private void discountProduct(Product product, int percent) {
		product.setPrice(product.getPrice() * (100.0 - percent) / 100.0);
	}
	
}
