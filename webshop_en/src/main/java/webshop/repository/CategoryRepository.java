package webshop.repository;

import java.util.List;

import webshop.model.Category;

public interface CategoryRepository {

	public Category save(Category category);
	
	public List<Category> findByName(String name);
}
