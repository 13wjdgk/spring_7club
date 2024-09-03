package com.mycom.myapp.controller;

import com.mycom.myapp.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.myapp.dto.ProductDto;
import com.mycom.myapp.dto.ProductResultDto;
import com.mycom.myapp.entity.Product;
import com.mycom.myapp.entity.User;
import com.mycom.myapp.service.ProductService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@ResponseBody
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping("/listproduct")
	public ProductResultDto listProduct() {

		ProductResultDto productResultDto = new ProductResultDto();

		try {
			
			productResultDto.setProductList(productService.listProduct());
			productResultDto.setResult("success");

		} catch (Exception e) {
			productResultDto.setResult("fail");
		}

		return productResultDto;
	}

	@PostMapping("/insertproduct")
	public ProductResultDto insertProduct(HttpSession session, Product product) {

		ProductResultDto productResultDto = new ProductResultDto();

		UserDto user = (UserDto) session.getAttribute("userDto");

		// 현재 session 유저의 role이 관리자(2:Manager)인 경우에만 insert 수행
		if (user != null && user.getRoles().get(2).equals("Manager")) {
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

		return productResultDto;
	}

	@PostMapping("/updateproduct")
	public ProductResultDto updateProduct(HttpSession session, Product product) {

		ProductResultDto productResultDto = new ProductResultDto();
		
		UserDto user = (UserDto) session.getAttribute("userDto");

		// 현재 session 유저의 role이 관리자(2:Manager)인 경우에만 update 수행
		if (user != null && user.getRoles().get(2).equals("Manager")) {
			try {
				// 관리자인 경우 update 수행
				productResultDto.setProductDto(productService.updateProduct(product));
				productResultDto.setResult("success");

			} catch (Exception e) {
				productResultDto.setResult("fail");
			}
		} else {
			// 관리자가 아닌 경우, "unauthorized"
			productResultDto.setResult("unauthorized");
		}


		return productResultDto;
	}

}
