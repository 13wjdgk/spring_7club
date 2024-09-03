package com.mycom.myapp.service;

import java.util.List;

import com.mycom.myapp.dto.AddressDto;
import com.mycom.myapp.dto.UserDto;
import com.mycom.myapp.dto.UserResultDto;
import com.mycom.myapp.entity.User;

public interface UserService {
	UserDto detailUser(long id);
	UserDto updateUser(long id,UserDto userDto);
	List<AddressDto> listUserAddress(long id);
	AddressDto saveUserAddress(AddressDto addressDto, long id);
	List<UserDto> listUser(long id);



	UserResultDto login(String email, String password);

	UserResultDto insertUser(User user);

	UserResultDto insertManager(User user);

}
