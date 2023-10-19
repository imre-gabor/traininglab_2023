package webshop.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

	private int id;
	private String name;
	
	private List<Product> products;
	
	
	public void addProduct(Product p) {
		p.setCategory(this);
		if(this.products == null)
			this.products = new ArrayList<>();
		
		this.products.add(p);
	}
}
