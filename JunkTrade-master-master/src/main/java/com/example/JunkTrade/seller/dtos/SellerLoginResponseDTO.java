package com.example.JunkTrade.seller.dtos;

import lombok.Data;

@Data
public class SellerLoginResponseDTO {
    private String Sellertoken;
    Long SellerId;
    boolean Status=true;
}
