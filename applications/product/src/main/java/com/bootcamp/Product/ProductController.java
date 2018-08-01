package com.bootcamp.Product;

import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}/name")
    public ResponseEntity<String> getName(@PathVariable(name="id") Integer id){
        return new ResponseEntity<>(getById(id).getBody().getName(), HttpStatus.OK);
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
