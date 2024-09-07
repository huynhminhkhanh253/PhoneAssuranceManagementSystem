package com.api.ManagerService.mapper;

import com.api.ManagerService.model.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    void addToDb(Product product);
    Product getProductByImei(String imei);
}
