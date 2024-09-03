package com.mycom.myapp.dto;

import java.time.LocalDate;

import com.mycom.myapp.entity.Product;
import com.mycom.myapp.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrdersDto {
	private int id;

	private User user;

	private Product product;

	private int orderQuantity;

	private LocalDate orderDate;
}
