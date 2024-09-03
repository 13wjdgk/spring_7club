package com.mycom.myapp.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersCreateDto {
	private Long userId;
    private int productId;
    private int orderQuantity;
    private LocalDate orderDate;
}
