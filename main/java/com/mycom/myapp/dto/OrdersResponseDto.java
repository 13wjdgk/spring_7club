package com.mycom.myapp.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class OrdersResponseDto {
    private int id;
    private Long userId;
    private String userName;
    private int productId;
    private String productName;
    private int orderQuantity;
    private LocalDate orderDate;
}
