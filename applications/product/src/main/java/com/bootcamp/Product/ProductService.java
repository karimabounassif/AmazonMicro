package com.bootcamp.Product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {this.productRepo = productRepo;}

    public ResponseEntity<Product> addProduct(Product product){
        productRepo.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Product>> getAll(){
        List<Product> products = productRepo.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<Product> getProduct(Integer id){
        return new ResponseEntity<>(productRepo.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<Product> updateProduct(Product product, Integer id){
        Product current = getProduct(id).getBody();
        current.setName(product.getName());
        current.setDescription(product.getDescription());
        current.setImage(product.getImage());
        current.setPrice(product.getPrice());
        productRepo.save(current);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteProduct(Integer id){
        productRepo.delete(getProduct(id).getBody());
        return new ResponseEntity<>("Deleted.", HttpStatus.NO_CONTENT);
    }
}
