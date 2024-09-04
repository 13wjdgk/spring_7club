package com.mycom.myapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.dto.ProductResultDto;
import com.mycom.myapp.dto.UserDto;
import com.mycom.myapp.entity.Product;
import com.mycom.myapp.entity.User;
import com.mycom.myapp.service.ProductService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<ProductResultDto> listProduct() {

		ProductResultDto productResultDto = new ProductResultDto();

		try {
			
			productResultDto.setProductList(productService.listProduct());
			productResultDto.setResult("success");

		} catch (Exception e) {
			productResultDto.setResult("fail");
		}

		if ("success".equals(productResultDto.getResult())) {
			return ResponseEntity.ok().body(productResultDto);
		} else if ("fail".equals(productResultDto.getResult())) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping("/products")
	public ResponseEntity<ProductResultDto> insertProduct(HttpSession session, @RequestBody Product product) {

		ProductResultDto productResultDto = new ProductResultDto();

		UserDto userDto = (UserDto) session.getAttribute("userDto");

		// 현재 session 유저의 role이 관리자(2:Manager)인 경우에만 insert 수행
		if (userDto != null && userDto.getRoles().get(2).equals("Manager")) {
			try {
				// 관리자인 경우 insert 수행
				productResultDto.setProductDto(productService.insertProduct(product));
				productResultDto.setResult("success");

			} catch (Exception e) {
				productResultDto.setResult("fail");
			}
		} else {
			// 관리자가 아닌 경우, "unauthorized"
			productResultDto.setResult("unauthorized");
		}

		if ("success".equals(productResultDto.getResult())) {
			return ResponseEntity.ok().body(productResultDto);
		} else if ("fail".equals(productResultDto.getResult())) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<ProductResultDto> updateProduct(HttpSession session, @PathVariable("id") int id, @RequestBody Product product) {

		ProductResultDto productResultDto = new ProductResultDto();
		
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		// 현재 session 유저의 role이 관리자(2:Manager)인 경우에만 update 수행
		if (userDto != null && userDto.getRoles().get(2).equals("Manager") ) {
			try {
				// 관리자인 경우 update 수행
				product.setId(id);
				productResultDto.setProductDto(productService.updateProduct(product));
				productResultDto.setResult("success");

			} catch (Exception e) {
				productResultDto.setResult("fail");
			}
		} else {
			// 관리자가 아닌 경우, "unauthorized"
			productResultDto.setResult("unauthorized");
		}

		// ResultStudentDto 의 result 에 대응
		if ("success".equals(productResultDto.getResult())) {
			return ResponseEntity.ok().body(productResultDto);
		} else if ("fail".equals(productResultDto.getResult())) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}

}
