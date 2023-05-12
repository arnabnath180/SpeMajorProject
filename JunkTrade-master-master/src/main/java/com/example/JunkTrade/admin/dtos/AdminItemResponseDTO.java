package com.example.JunkTrade.admin.dtos;

import lombok.Data;

@Data
public class AdminItemResponseDTO {
    private String item;
    private int quantity;
    private int rate;
}
