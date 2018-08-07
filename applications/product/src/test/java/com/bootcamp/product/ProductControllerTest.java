package com.bootcamp.product;

import com.bootcamp.Product.Product;
import com.bootcamp.Product.ProductController;
import com.bootcamp.Product.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {

    @Autowired
    ProductController productController;

    Product product = new Product();

    @Before
    public void setup(){
        product.setPrice(150.0);
        product.setImage("k");
        product.setDescription("asd");
        product.setName("Test");
        Assert.assertEquals(productController.addProduct(product).getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testGetById(){
        Assert.assertEquals(productController.getById(product.getId()).getBody().getName(),
                "Test");
    }

    @Test
    public void testGetName(){
        Assert.assertEquals(productController.getName(product.getId()).getBody(), "Test");
    }

    @Test
    public void testGetAll(){
        Assert.assertNotNull(productController.getAll());
    }

    @Test
    public void testDeleteProduct(){
        Product newProduct = new Product();
        productController.addProduct(newProduct);
        Assert.assertEquals(productController.deleteProduct(newProduct.getId()).getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testUpdateProduct(){
        Product newProduct = new Product();
        newProduct.setName("First");
        productController.addProduct(newProduct);
        Assert.assertEquals(productController.getById(newProduct.getId()).getBody().getName(), "First");
        newProduct.setName("Second");
        productController.updateProduct(newProduct.getId(), newProduct);
        Assert.assertEquals(productController.getById(newProduct.getId()).getBody().getName(), "Second");
    }

}
