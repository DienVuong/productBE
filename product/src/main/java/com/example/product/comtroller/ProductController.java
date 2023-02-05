package com.example.product.comtroller;

import com.example.product.model.Category;
import com.example.product.model.Product;
import com.example.product.service.ICategoryService;
import com.example.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping()
    public ResponseEntity<Iterable<Product>> getAllProduct() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> saveProduct (@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Iterable<Product>> update(@PathVariable Long id,
                                                    @RequestBody Product product) {
        Optional<Product> productOptional = productService.findById(id);
        if(productOptional.isPresent()) {
            product.setId(productOptional.get().getId());
            productService.save(product);
            return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Iterable<Product>> delete(@PathVariable Long id) {
        productService.remove(id);
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<Iterable<Product>> search(@RequestParam("search") String name) {
        return new ResponseEntity<>(productService.findAllByNameContaining(name), HttpStatus.OK);
    }

    @GetMapping("filter/category/{id}")
    public ResponseEntity<Iterable<Product>> filterByCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if(categoryOptional.isPresent()) {
            return new ResponseEntity<>(productService.findAllCategory(categoryOptional.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if(product.isPresent()){
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
