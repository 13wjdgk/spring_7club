package com.mycom.myapp.controller;
import org.springframework.web.bind.annotation.*;
import com.mycom.myapp.dto.OrdersCreateDto;
import com.mycom.myapp.dto.OrdersDetailResponseDto;
import com.mycom.myapp.dto.OrdersResponseDto;
import com.mycom.myapp.dto.OrdersUserProductDto;
import com.mycom.myapp.service.OrdersService;
import lombok.RequiredArgsConstructor;
import java.util.List;
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;
    
    @PostMapping("/orders")
    public OrdersResponseDto createOrder(@RequestBody OrdersCreateDto ordersCreateDto) {
        return ordersService.createOrder(ordersCreateDto);
    }
    
    @GetMapping("/orders/listordersuserproduct")
    public List<OrdersUserProductDto> listOrdersUserProduct() {
        return ordersService.listOrdersUserProduct();
    }
    
    @GetMapping("/orders/detail/{orderId}")
    public OrdersDetailResponseDto getOrderDetail(@PathVariable("orderId") int orderId) {
        return ordersService.getOrderDetail(orderId);
    }
}