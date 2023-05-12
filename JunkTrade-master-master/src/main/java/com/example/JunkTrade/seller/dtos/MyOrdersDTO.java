package com.example.JunkTrade.seller.dtos;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class MyOrdersDTO {
    private Long id;
    private int price;
    private Date pickupDate;
    private Time time;
    private String category;

}
