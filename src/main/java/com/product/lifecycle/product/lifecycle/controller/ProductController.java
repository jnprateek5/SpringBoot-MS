package com.product.lifecycle.product.lifecycle.controller;


import com.product.lifecycle.product.lifecycle.Model.Product;
import com.product.lifecycle.product.lifecycle.Service.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{productId}")
    @ResponseBody
    public Product getProduct(@PathVariable String productId){

        return productService.getProduct(productId);
    }

    @PostMapping("/product")
    public String addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
}
