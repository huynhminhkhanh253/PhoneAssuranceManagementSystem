package com.api.ManagerService.service;

import com.api.ManagerService.mapper.OrderMapper;
import com.api.ManagerService.model.Order;
import com.api.ManagerService.model.commonData.Specs;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Transactional
    public void addOrUpdateOrder(Order order, Specs specs){
        Order existingOrder = orderMapper.getOrderByImei(order.getImei());
        if(existingOrder != null){
            if(order.getIneligible_reason() != null || order.getPending_reason() != null){
                orderMapper.updateToDb(order);
            }
        }
        else {
            orderMapper.addSpecs(specs);
            order.setSpecs_id(specs.getId());
            orderMapper.addToDb(order);
        }

    }
    public ArrayList<Order> displayAllOrder(int page, int size){
        int offset = (page - 1) * size;
        // ex: total 30
        // page, size
        // page    offset
        // 1 10       0
        // 2 10       10
        // 3 10       20
        return orderMapper.getAllOrder(offset, size);
    }
    public Order findOrderByImei(String imei){
        return orderMapper.getOrderByImei(imei);
    }
}
