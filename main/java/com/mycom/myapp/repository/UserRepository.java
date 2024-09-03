package com.mycom.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mycom.myapp.dto.UserDto;
import com.mycom.myapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	// @Query("UPDATE User u SET u.name = :name, u.email = :email, u.password = :password, u.phone = :phone, u.gender = :gender WHERE u.id = :id")
	// User updateUser(@Param("name") String name,
	// 	@Param("email") String email,
	// 	@Param("password") String password,
	// 	@Param("phone") String phone,
	// 	@Param("gender") Character gender,
	// 	@Param("id") Long id);




	//@Query("SELECT u FROM User u join u.roles r where r.roleName = 'Customer'")
//	@Query("select new com.mycom.myapp.dto.UserDto(u.id,u.name,u.email,u.password,u.phone,u.gender ) FROM User u join u.roles r where r.roleName = 'Customer'")
//	List<UserDto> findCustomerUser();

	User findByEmail(String var1);

}
