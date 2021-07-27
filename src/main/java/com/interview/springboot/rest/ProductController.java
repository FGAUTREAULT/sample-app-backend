package com.interview.springboot.rest;

import java.util.concurrent.atomic.AtomicInteger;

import com.interview.springboot.rest.model.Product;
import com.interview.springboot.rest.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/products")
@RestController
@CrossOrigin
@Api(value = "products", description = "Operations to manage products.")
public class ProductController {

    private final AtomicInteger counter = new AtomicInteger();

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Get the list of products from the database")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Product> list(Model model) {
        Iterable<Product> productList = this.productService.listAllProducts();
        return productList;
    }

    @ApiOperation(value = "Get the target product from its id field", response = Product.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product read(@PathVariable Integer id, Model model) {
        Product product = this.productService.getProductById(id);
        return product;
    }

    @ApiOperation(value = "Create & save the product in the database")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        product.setId(counter.incrementAndGet());
        this.productService.create(product);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @ApiOperation(value = "Update the target product from its id field and recalculate operation")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody Product product) {
        Product storedProduct = this.productService.getProductById(id);
        storedProduct.setDescription(product.getDescription());
        storedProduct.setProductId(product.getProductId());
        storedProduct.setVersion(storedProduct.getVersion() + 1);
        this.productService.create(storedProduct);
        return new ResponseEntity<String>("Product updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete the target product from its id field")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        this.productService.deleteProduct(id);
        return new ResponseEntity<String>("Product deleted successfully", HttpStatus.OK);

    }

}