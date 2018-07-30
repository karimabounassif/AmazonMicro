package com.bootcamp.Product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {this.productService = productService;}

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable(name="id") Integer id){
        return productService.getProduct(id);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return productService.getAll();
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name="id") Integer id){
        return productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(name="id") Integer id, @RequestBody Product product){
        return productService.updateProduct(product, id);
    }
}
