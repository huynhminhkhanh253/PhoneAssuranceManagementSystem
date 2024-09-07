package com.api.ManagerService.controller;

import com.api.ManagerService.model.Product;
import com.api.ManagerService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping("/add")
    public void addProductToDb(@RequestBody Product product){
        productService.addProduct(product);
    }
    @GetMapping("/get/{imei}")
    public Product getProductByImei(@PathVariable String imei){
        return productService.getProductByImei(imei);
    }

}
