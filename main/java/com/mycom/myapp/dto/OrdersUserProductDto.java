package com.mycom.myapp.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersUserProductDto {
	private int orderId;
	private String userName;
	private String userPhone;
	private String productName;
	private int productPrice;
	private LocalDate orderDate;
}
