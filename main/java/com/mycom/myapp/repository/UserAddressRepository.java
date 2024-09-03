package com.mycom.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mycom.myapp.dto.AddressDto;
import com.mycom.myapp.entity.UserAddress;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
	@Query("SELECT new com.mycom.myapp.dto.AddressDto(ua.id,ua.zipCode,ua.streetName) FROM UserAddress ua WHERE ua.user.id = :userId")
	List<AddressDto> findAddressByUserId(Long userId);

}
