package com.mycom.myapp.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class OrdersDetailResponseDto {
    private int id;
    private Long userId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private int productId;
    private String productName;
    private int productPrice;
    private int orderQuantity;
    private LocalDate orderDate;
    private int totalPrice;
}