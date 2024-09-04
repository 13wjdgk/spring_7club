package com.mycom.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mycom.myapp.dto.UserDto;
import com.mycom.myapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select new com.mycom.myapp.dto.UserDto(u.id,u.name,u.email,u.password,u.gender,u.phone ) FROM User u join u.roles r where r.roleName = 'Customer'")
	List<UserDto> findCustomerUser();

	User findByEmail(String var1);

}
