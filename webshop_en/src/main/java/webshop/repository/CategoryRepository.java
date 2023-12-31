package webshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{	
	
	public List<Category> findByName(String name);
}
