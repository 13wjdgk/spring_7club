package com.mycom.myapp.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mycom.myapp.entity.User;
import com.mycom.myapp.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Long id;

	private String name;

	private String email;

	private String password;
	private char gender;
	private String phone;
	private Map<Integer,String> roles = new HashMap();
	//	public UserDto(Long id, String name, String email, String password, char gender, String phone) {
//		this.id = id;
//		this.name = name;
//		this.email = email;
//		this.password = password;
//		this.gender = gender;
//		this.phone = phone;
//
//	}



	public UserDto(User user){
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.gender = user.getGender();
		this.phone = user.getPhone();
	}
}
