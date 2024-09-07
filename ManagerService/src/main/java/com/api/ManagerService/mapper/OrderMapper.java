package com.api.ManagerService.mapper;

import com.api.ManagerService.model.Order;
import com.api.ManagerService.model.commonData.Specs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface OrderMapper {
    void addToDb(Order order);
   ArrayList<Order> getAllOrder(@Param("offset") int offset, @Param("limit") int limit);
   Order getOrderByImei(String imei);
   void addSpecs(Specs specs);
   void updateToDb(Order order);
}

