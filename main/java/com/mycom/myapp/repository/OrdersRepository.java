package com.mycom.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mycom.myapp.dto.OrdersUserProductDto;
import com.mycom.myapp.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{
	
	@Query("""
            select new com.mycom.myapp.dto.OrdersUserProductDto( 
                    o.id as orderId, 
                    u.name as userName, u.phone userPhone, 
                    p.name as productName, p.price as productPrice, o.orderDate 
            )  
              from Orders o inner join o.user u
                                  join o.product p
    """) 
    List<OrdersUserProductDto> listOrdersCustomerProduct();
}
