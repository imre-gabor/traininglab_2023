package webshop.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import webshop.model.Product;

@Getter
@Setter
public class CategoryDto {
    private int id;
    private String name;
    
    private List<ProductDto> products;
}
