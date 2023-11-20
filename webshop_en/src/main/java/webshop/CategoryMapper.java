package webshop;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import webshop.dto.CategoryDto;
import webshop.dto.ProductDto;
import webshop.model.Category;
import webshop.model.Product;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto categoryToDto(Category category);
    
    List<CategoryDto> categoriesToDtos(List<Category> categories);
    
    Category dtoToCategory(CategoryDto categoryDto);
    
    @Mapping(target = "category", ignore = true)
    ProductDto productToDto(Product product);
    
}
