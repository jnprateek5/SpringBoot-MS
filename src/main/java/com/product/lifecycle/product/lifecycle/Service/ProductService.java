package com.product.lifecycle.product.lifecycle.Service;

import com.product.lifecycle.product.lifecycle.Model.Product;
import com.product.lifecycle.product.lifecycle.Repository.MongoProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class ProductService {

    @Autowired
    private MongoProductRepository mongoRepository;
    public String addProduct(Product product) {
        return mongoRepository.insertRecord(product);
    }

    public Product getProduct(String productId){
        return mongoRepository.getRecord(productId);
    }

}
