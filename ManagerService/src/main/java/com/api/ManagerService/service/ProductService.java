package com.api.ManagerService.service;

import com.api.ManagerService.mapper.ProductMapper;
import com.api.ManagerService.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;
    public void addProduct(Product product){
        productMapper.addToDb(product);
    }

    public Product getProductByImei(String imei){
        return productMapper.getProductByImei(imei);
    }
}
