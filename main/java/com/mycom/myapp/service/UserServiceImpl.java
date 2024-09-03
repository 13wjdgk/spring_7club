package com.mycom.myapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mycom.myapp.dto.AddressDto;
import com.mycom.myapp.dto.UserDto;
import com.mycom.myapp.dto.UserResultDto;
import com.mycom.myapp.entity.User;
import com.mycom.myapp.entity.UserAddress;
import com.mycom.myapp.entity.UserRole;
import com.mycom.myapp.repository.UserAddressRepository;
import com.mycom.myapp.repository.UserRepository;
import com.mycom.myapp.repository.UserRoleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;
	private final UserAddressRepository userAddressRepository;
	private final UserRoleRepository userRoleRepository;
	@Override
	public UserDto detailUser(long id) {
		UserDto userDto = new UserDto();
		userRepository.findById(id).ifPresent(user -> {
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
			userDto.setGender(user.getGender());
			userDto.setPhone(user.getPhone());
		});
		return userDto;
	}

	@Override
	public UserDto updateUser(long id , UserDto userDto) {
		System.out.println("userDto : " + userDto);
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
		user.setPhone(userDto.getPhone());
		user.setGender(userDto.getGender());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());

		 return new UserDto(userRepository.save(user));
		// return new UserDto(userRepository.updateUser(userDto.getName(), userDto.getEmail(), userDto.getPassword(), userDto.getPhone(), userDto.getGender(), userDto.getId()));
	}

	@Override
	public List<AddressDto> listUserAddress(long id) {
		return userAddressRepository.findAddressByUserId(id);
	}

	@Override
	public AddressDto saveUserAddress(AddressDto addressDto, long id) {
		UserAddress userAddress = new UserAddress(addressDto);
		userRepository.findById(id).ifPresent(user -> {
			userAddress.setUser(user);
		});
		return new AddressDto(userAddressRepository.save(userAddress));
	}

	@Override
	public List<UserDto> listUser(long id) {
		System.out.println("id : " + id);
		System.out.println("userRepository.findById(id) : " + userRepository.findById(id));
		//User user = userRepository.findById(id).orElseThrow(() -> {new IllegalArgumentException("해당 사용자가 없습니다.")});
		// if(user.getRoles().stream().anyMatch(role -> role.getRoleName().equals("Manager"))) {
		// 	return userRepository.findCustomerUser();
		// }
		throw new IllegalArgumentException("관리자만 접근 가능합니다.");
	}

	@Override
	public UserResultDto login(String email, String password) {
		UserResultDto userResultDto = new UserResultDto();
		User user = this.userRepository.findByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
			userDto.setGender(user.getGender());
			 Map<Integer, String> userRoles = userDto.getRoles();
			 user.getRoles().forEach((role) -> {
				 if(role.getId() ==1){
					 userResultDto.setUserRoleId(1);
				 } else if (role.getId() == 2) {
					 userResultDto.setUserRoleId(2);
				 }
				 userRoles.put(role.getId(), role.getRoleName());
			 });
			userResultDto.setUserDto(userDto);
			userResultDto.setResult("success");
		} else {
			userResultDto.setResult("fail");
		}

		return userResultDto;
	}

	@Override
	public UserResultDto insertUser(User user) {
		UserResultDto userResultDto = new UserResultDto();

		try {
			UserRole userRole = new UserRole();
			userRole.setId(1);
			userRole.setRoleName("Customer");
			userRoleRepository.save(userRole);
			user.getRoles().add(userRole);
			this.userRepository.save(user);
			userResultDto.setResult("success");
		} catch (Exception var4) {
			var4.printStackTrace();
			userResultDto.setResult("fail");
		}

		return userResultDto;
	}

	@Override
	public UserResultDto insertManager(User user) {
		UserResultDto userResultDto = new UserResultDto();

		try {
			UserRole userRole = new UserRole();
			userRole.setId(2);
			userRole.setRoleName("Manager");
			this.userRoleRepository.save(userRole);
			user.getRoles().add(userRole);
			this.userRepository.save(user);
			userResultDto.setResult("success");
		} catch (Exception var4) {
			var4.printStackTrace();
			userResultDto.setResult("fail");
		}

		return userResultDto;
	}
}
