package com.mycom.myapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.myapp.dto.AddressDto;
import com.mycom.myapp.dto.ResultDto;
import com.mycom.myapp.dto.UserDto;
import com.mycom.myapp.dto.UserResultDto;
import com.mycom.myapp.entity.User;
import com.mycom.myapp.repository.UserRepository;
import com.mycom.myapp.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@ResponseBody
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final UserRepository userRepository;

	@GetMapping("/")
	public String index(HttpSession session) {
//		List<UserDto> users = userRepository.findCustomerUser();
//
//		System.out.println(userRepository.findCustomerUser());

		// session.setAttribute("id", 3L);
		// System.out.println("index" + session.getAttribute("id"));
		return "index_가.html";
	}

	/**
	 * 회원정보 조회
	 * @param session
	 * @return UserDto
	 */
	// @GetMapping("/detail")
	// public UserDto detail(HttpSession session) {
	// 	return userService.detailUser((long)session.getAttribute("id"));
	// }
	@GetMapping("/detail")
	public ResultDto<UserDto> detail(HttpSession session) {
		UserDto sessionValue = (UserDto) session.getAttribute("userDto");
		UserDto result = userService.detailUser(sessionValue.getId());

		if(result==null) {
			return new ResultDto<UserDto>("fail", result);
		}
		return new ResultDto<UserDto>("success", result);
	}

	/**
	 * 회원정보 수정
	 * @param session
	 * @return UserDto
	 */
	@PostMapping("/update")
	public ResultDto<UserDto> update(HttpSession session,  UserDto userDto) {
		UserDto sessionValue = (UserDto) session.getAttribute("userDto");
		Long id = sessionValue.getId();
		System.out.println("id : " + id);
		if(id==null) {
			return  new ResultDto<UserDto>("fail", null);
		}
		UserDto result = userService.updateUser(id,userDto);
		if(result==null) {
			return new ResultDto<UserDto>("fail", result);
		}
		return new ResultDto<UserDto>("success", result);
	}


	/**
	 * 회원 주소 목록 조회
	 * @param session
	 * @return List<AddressDto>
	 */
	@GetMapping("/addressList")
	public ResultDto<List<AddressDto>> addressList(HttpSession session) {
		UserDto sessionValue = (UserDto) session.getAttribute("userDto");
		System.out.println("sessionValue : " + sessionValue);
		Long id = sessionValue.getId();
		if(id==null) {
			return  new ResultDto<List<AddressDto>>("fail", null);
		}
		List<AddressDto> result = userService.listUserAddress(id);
		if(result==null) {
			return new ResultDto<List<AddressDto>>("fail", result);
		}
		return new ResultDto<List<AddressDto>>("success", result);
	}
	/**
	 * 회원 주소 등록
	 * @param session
	 * @param addressDto
	 * @return AddressDto
	 */
	@PostMapping("/address")
	public ResultDto<AddressDto> addAddress(HttpSession session,AddressDto addressDto) {
		UserDto sessionValue = (UserDto) session.getAttribute("userDto");
		System.out.println("sessionValue : " + sessionValue);
		Long id = sessionValue.getId();
		if(id==null) {
			return  new ResultDto<AddressDto>("fail", null);
		}
		AddressDto result = userService.saveUserAddress(addressDto,id);
		if(result==null) {
			return new ResultDto<AddressDto>("fail", result);
		}
		return new ResultDto<AddressDto>("success", result);
	}

	/**
	 * 회원 목록 조회
	 * @param session
	 * @return AddressDto
	 */
	@GetMapping("/customerList")
	public ResultDto<List<UserDto>> customerList(HttpSession session) {
		UserDto sessionValue = (UserDto) session.getAttribute("userDto");
		Long id = sessionValue.getId();
		try{
			List<UserDto> result = userService.listUser(id);
			return new ResultDto<List<UserDto>>("success", result);
		}catch (Exception e){
			return new ResultDto<List<UserDto>>("fail", null);
		}
	}



	@PostMapping({"/login"})
	public UserResultDto login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
		UserResultDto userResultDto = this.userService.login(email, password);
		if (userResultDto.getResult().equals("success")) {
			session.setAttribute("userDto", userResultDto.getUserDto());
		}

		return userResultDto;
	}

	@GetMapping({"/logout"})
	public UserResultDto logout(HttpSession session) {
		UserResultDto userResultDto = new UserResultDto();

		try {
			session.invalidate();
			userResultDto.setResult("success");
		} catch (IllegalStateException var4) {
			userResultDto.setResult("fail");
		}

		return userResultDto;
	}

	@PostMapping({"/register"})
	public UserResultDto insertUser(User user) {
		return this.userService.insertUser(user);
	}

	@PostMapping({"/manager/register"})
	public UserResultDto insertManager(User user) {
		return this.userService.insertManager(user);
	}

}
