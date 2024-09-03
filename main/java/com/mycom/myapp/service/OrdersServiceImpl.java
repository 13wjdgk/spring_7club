package com.mycom.myapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycom.myapp.dto.OrdersCreateDto;
import com.mycom.myapp.dto.OrdersDetailResponseDto;
import com.mycom.myapp.dto.OrdersResponseDto;
import com.mycom.myapp.dto.OrdersUserProductDto;
import com.mycom.myapp.entity.Orders;
import com.mycom.myapp.entity.Product;
import com.mycom.myapp.entity.User;
import com.mycom.myapp.repository.OrdersRepository;
import com.mycom.myapp.repository.ProductRepository;
import com.mycom.myapp.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

	private final OrdersRepository ordersRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;

	@Override
	@Transactional
	public OrdersResponseDto createOrder(OrdersCreateDto ordersCreateDto) {
		User user = userRepository.findById(ordersCreateDto.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));

		Product product = productRepository.findById(ordersCreateDto.getProductId())
				.orElseThrow(() -> new RuntimeException("Product not found"));

		Orders order = new Orders();
		order.setUser(user);
		order.setProduct(product);
		order.setOrderQuantity(ordersCreateDto.getOrderQuantity());
		order.setOrderDate(ordersCreateDto.getOrderDate());

		Orders savedOrder = ordersRepository.save(order);

		return convertToResponseDto(savedOrder);
	}

	private OrdersResponseDto convertToResponseDto(Orders order) {
		OrdersResponseDto dto = new OrdersResponseDto();
		dto.setId(order.getId());
		dto.setUserId(order.getUser().getId());
		dto.setUserName(order.getUser().getName());
		dto.setProductId(order.getProduct().getId());
		dto.setProductName(order.getProduct().getName());
		dto.setOrderQuantity(order.getOrderQuantity());
		dto.setOrderDate(order.getOrderDate());
		return dto;
	}

	@Override
	public List<OrdersUserProductDto> listOrdersUserProduct() {
		return ordersRepository.listOrdersCustomerProduct();
	}

	@Override
	@Transactional
	public OrdersDetailResponseDto getOrderDetail(int orderId) {
		Orders order = ordersRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

		return convertToDetailResponseDto(order);
	}

	private OrdersDetailResponseDto convertToDetailResponseDto(Orders order) {
		OrdersDetailResponseDto dto = new OrdersDetailResponseDto();
		dto.setId(order.getId());
		dto.setUserId(order.getUser().getId());
		dto.setUserName(order.getUser().getName());
		dto.setUserEmail(order.getUser().getEmail());
		dto.setUserPhone(order.getUser().getPhone());
		dto.setProductId(order.getProduct().getId());
		dto.setProductName(order.getProduct().getName());
		dto.setProductPrice(order.getProduct().getPrice());
		dto.setOrderQuantity(order.getOrderQuantity());
		dto.setOrderDate(order.getOrderDate());
		dto.setTotalPrice(order.getOrderQuantity() * order.getProduct().getPrice());
		return dto;
	}
}
