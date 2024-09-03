package com.mycom.myapp.service;

import java.util.List;

import com.mycom.myapp.dto.ProductDto;
import com.mycom.myapp.entity.Product;

public interface ProductService {
	
	// 1) (관리자+회원) 상품 목록 조회
	List<ProductDto> listProduct();
	
	// 2) (관리자) 상품 등록
	ProductDto insertProduct(Product product);
	
	// 3) (관리자) 상품 수정
	ProductDto updateProduct(Product product);

}
