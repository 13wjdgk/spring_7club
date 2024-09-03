package com.mycom.myapp.dto;

import java.util.List;


import lombok.Data;

@Data
public class ProductResultDto {

	private String result;
	private ProductDto productDto;
	private List<ProductDto> ProductList;
}
