package com.interview.springboot.rest.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import com.interview.springboot.rest.model.Product;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    // innerDB for example
    private Set<Product> products = new HashSet<Product>();

    public Iterable<Product> listAllProducts() {
        return products;
    }

    public Product getProductById(Integer id) {
        return products.stream().filter(product -> product.getId().equals(id)).findAny().orElse(null);
    }

    public void create(Product product) {
        this.calculateOperationResult(product);
        this.products.add(product);
    }

    public void deleteProduct(Integer id) {
        final Product product = this.getProductById(id);
        this.products.remove(product);
    }

    private void calculateOperationResult(Product product) {

        System.out.println(String.format("App: Calculating the %s Product operation - of given input arguments...",
                product.getProductId()));

        switch (product.getProductId()) {
            case "Sum":
                product.setResult(IntStream.of(product.getInputs()).sum() + "");
                break;
            case "Sub":
                int result = product.getInputs()[0];
                for (int i = 0; i < product.getInputs().length; i++) {
                    if (i < product.getInputs().length - 1) {
                        result = result - product.getInputs()[i + 1];
                    }
                }
                product.setResult(result + "");
                break;
            case "Odd":
                Integer result2 = null;
                int i = 0;
                while (i < product.getInputs().length) {
                    if (product.getInputs()[i] % 2 != 0 && (result2 == null || product.getInputs()[i] < result2)) {
                        result2 = product.getInputs()[i];
                    }
                    i = i + 1;
                }
                product.setResult(result2 + "");
                break;
        }
        System.out.println(String.format("App: %s Product result is %s", product.getProductId(), product.getResult()));
    }

}
