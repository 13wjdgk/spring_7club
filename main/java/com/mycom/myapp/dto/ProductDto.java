package com.mycom.myapp.dto;

import java.util.List;

import com.mycom.myapp.entity.Orders;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
	private int id;
    private String name;
    private int price;
//    private List<Orders> orders;
}
