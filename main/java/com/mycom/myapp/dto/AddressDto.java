package com.mycom.myapp.dto;

import com.mycom.myapp.entity.UserAddress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class AddressDto {
	private Long id;
	private String zipCode;
	private String streetName;
	public AddressDto(UserAddress userAddress) {
		this.id = userAddress.getId();
		this.zipCode = userAddress.getZipCode();
		this.streetName = userAddress.getStreetName();
	}
}
