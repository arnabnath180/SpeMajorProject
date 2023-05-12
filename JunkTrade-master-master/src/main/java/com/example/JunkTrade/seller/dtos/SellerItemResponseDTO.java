package com.example.JunkTrade.seller.dtos;

import lombok.Data;

@Data
public class SellerItemResponseDTO {
    private String item;
    private int quantity;
    private int rate;
}
