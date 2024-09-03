package com.mycom.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.myapp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
