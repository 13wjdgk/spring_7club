package com.mycom.myapp.dto;

import java.util.List;

import lombok.Data;
import lombok.Generated;
@Data
public class UserResultDto {
	private String result;
	private UserDto userDto;
	private Integer userRoleId;
	private List<UserDto> userList;



}
