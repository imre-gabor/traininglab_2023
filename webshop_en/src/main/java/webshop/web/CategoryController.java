package webshop.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import webshop.CategoryMapper;
import webshop.dto.CategoryDto;
import webshop.repository.CategoryRepository;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    
    @PostMapping
    public CategoryDto create(@RequestBody CategoryDto category) {
        category.setId(0);
        return categoryMapper.categoryToDto(
                categoryRepository.save(categoryMapper.dtoToCategory(category))
               );
    }
    
    @GetMapping
    public List<CategoryDto> findAll() {
        return categoryMapper.categoriesToDtos(categoryRepository.findAll());
    }
    
    
//    @GetMapping("/{id}")
//    public ResponseEntity<CategoryDto> findById(@PathVariable int id) {
//        Optional<Category> optCat = categoryRepository.findById(id);
//        if(optCat.isPresent()) {
//            return ResponseEntity.ok(categoryMapper.categoryToDto(optCat.get()));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    
    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable int id) {
        return categoryMapper.categoryToDto(categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        categoryRepository.deleteById(id);
    }
    
    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable int id, @RequestBody CategoryDto categoryDto) {
        categoryDto.setId(id);
        
        return categoryMapper.categoryToDto(
                categoryRepository.save(categoryMapper.dtoToCategory(categoryDto))
               );
    }
    
}
