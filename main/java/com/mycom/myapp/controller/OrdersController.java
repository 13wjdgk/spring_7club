package com.mycom.myapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.myapp.dto.OrdersCreateDto;
import com.mycom.myapp.dto.OrdersDetailResponseDto;
import com.mycom.myapp.dto.OrdersResponseDto;
import com.mycom.myapp.dto.OrdersUserProductDto;
import com.mycom.myapp.service.OrdersService;

import lombok.RequiredArgsConstructor;

@Controller
@ResponseBody
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

	private final OrdersService ordersService;
	
	@PostMapping
    public ResponseEntity<OrdersResponseDto> createOrder(@RequestBody OrdersCreateDto ordersCreateDto) {
        OrdersResponseDto createdOrder = ordersService.createOrder(ordersCreateDto);
        return ResponseEntity.ok(createdOrder);
    }
	
	@GetMapping("/listordersuserproduct")
	public List<OrdersUserProductDto> listOrdersUserProduct() {
		List<OrdersUserProductDto> ordersUserProductDtoList = ordersService.listOrdersUserProduct();
		return ordersUserProductDtoList;
	}
	
    @GetMapping("/detail/{orderId}")
    public ResponseEntity<OrdersDetailResponseDto> getOrderDetail(@PathVariable("orderId") int orderId) {
        OrdersDetailResponseDto orderDetail = ordersService.getOrderDetail(orderId);
        return ResponseEntity.ok(orderDetail);
    }
}
