package com.mycom.myapp.service;

import java.util.List;

import com.mycom.myapp.dto.OrdersCreateDto;
import com.mycom.myapp.dto.OrdersDetailResponseDto;
import com.mycom.myapp.dto.OrdersResponseDto;
import com.mycom.myapp.dto.OrdersUserProductDto;

public interface OrdersService {
	
	OrdersResponseDto createOrder(OrdersCreateDto ordersCreateDto);

	List<OrdersUserProductDto> listOrdersUserProduct();
	
	OrdersDetailResponseDto getOrderDetail(int orderId);
}
