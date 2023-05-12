package com.example.JunkTrade.admin.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
@Getter
@Setter
public class AdminResponseDTO {

    private Long id;
    private String fname;
    private String lname;
    private String email;
    private String phoneNumber;
    private int price;
    private Date pickupDate;
    private Time time;

    private String address;


    public AdminResponseDTO(Long id, String fname, String lname, String email, String phoneNumber, int price, Date pickupDate, Time time, String address) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.pickupDate = pickupDate;
        this.time = time;
        this.address = address;
        this.id=id;
    }
    public AdminResponseDTO(){}


}
