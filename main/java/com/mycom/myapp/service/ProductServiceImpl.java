package com.mycom.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mycom.myapp.dto.ProductDto;
import com.mycom.myapp.entity.Product;
import com.mycom.myapp.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	public List<ProductDto> listProduct() {

		// Product 객체 리스트를 ProductDto 객체 리스트로 변환할 새로운 리스트 생성
		List<ProductDto> productDtoList = new ArrayList<>();

		// ProductRepository에서 모든 Product 엔티티를 가져옴
		List<Product> productList = productRepository.findAll();

		// 각 Product 객체를 ProductDto로 변환하여 productDtoList에 추가
		productList.forEach(product -> {
			ProductDto productDto = new ProductDto();
			productDto.setId(product.getId());
			productDto.setName(product.getName());
//			productDto.setOrders(product.getOrders()); // 필요에 따라 변환 로직을 추가할 수 있음
			productDto.setPrice(product.getPrice());

			productDtoList.add(productDto);
		});

		// 변환된 ProductDto 리스트를 반환
		return productDtoList;
	}

	@Override
	public ProductDto insertProduct(Product product) {
		// 저장된 Product 엔티티 객체
		Product savedProduct = productRepository.save(product);

		// Product 엔티티를 ProductDto로 변환
		return convertToDto(savedProduct);
	}

	@Override
	public ProductDto updateProduct(Product product) {
		System.out.println("product : " + product);
		// 수정된 Product 엔티티 객체
		Product updatedProduct = productRepository.save(product);

		// Product 엔티티를 ProductDto로 변환
		return convertToDto(updatedProduct);
	}

	// Product를 ProductDto로 변환하는 메서드
	private ProductDto convertToDto(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setName(product.getName());
//		productDto.setOrders(product.getOrders()); // 필요에 따라 변환 로직을 수정할 수 있음
		productDto.setPrice(product.getPrice());

		return productDto;
	}

}
