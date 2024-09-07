package com.api.ManagerService.controller;

import com.api.ManagerService.model.Order;
import com.api.ManagerService.service.JwtService;
import com.api.ManagerService.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("managerService")
@CrossOrigin
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    JwtService jwtService;


    @GetMapping("order/getAllOrder")
    @Operation(summary = "Get a user by ID")
    public ResponseEntity<Object> getAll(@RequestHeader(value = "Authorization", required = false) String token,
                                         @RequestParam(name = "page", defaultValue = "1") int page,
                                         @RequestParam(name = "size", defaultValue = "10") int size){

        if(token == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is required");
        }
        else {
            String realToken = token.substring(7);
            String tokenCheckResult = jwtService.validateToken(realToken);
            if(tokenCheckResult.equalsIgnoreCase("valid")){
                ArrayList<Order> orders = orderService.displayAllOrder(page, size);
                return new ResponseEntity<>(orders, HttpStatus.OK);
            }
            else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(tokenCheckResult);
        }
    }
    @GetMapping("order/getOrderByImei/{imei}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class)) }),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content) })
    public ResponseEntity<Object> getOrderImei(@PathVariable String imei, @RequestHeader(value = "Authorization", required = false) String token){
        if(token == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is required");
        }
        else {
            String realToken = token.substring(7);
            String tokenCheckResult = jwtService.validateToken(realToken);
            if(tokenCheckResult.equalsIgnoreCase("valid")){
                Order order = orderService.findOrderByImei(imei);;
                return new ResponseEntity<>(order, HttpStatus.OK);
            }
            else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(tokenCheckResult);
        }
    }
}