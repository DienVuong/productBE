package com.example.product.comtroller;

import com.example.product.model.Category;
import com.example.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping()
    public ResponseEntity<Iterable<Category>> getAllCategory(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<Iterable<Category>> save(@RequestBody Category category) {
        categoryService.save(category);
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Iterable<Category>> delete(@PathVariable Long id) {
        categoryService.remove(id);
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if(categoryOptional.isPresent()) {
            return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
