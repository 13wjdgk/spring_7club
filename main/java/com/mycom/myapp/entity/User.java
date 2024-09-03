package com.mycom.myapp.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String email;

	private String password;
	private char gender;
	private String phone;

	@ManyToMany
	@JoinTable(
		name = "user_user_role",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<UserRole> roles = new HashSet<>();

	@OneToMany(mappedBy = "user")
	@ToString.Exclude
	private List<UserAddress> addresses = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	@ToString.Exclude
	private List<Orders> orders;


}
