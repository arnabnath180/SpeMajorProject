package com.example.JunkTrade.seller.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScrapItemListDTO {
    private String item;
    private int quantity;
    private int rate;


    public ScrapItemListDTO(
            @JsonProperty("item") String item,
            @JsonProperty("quantity") int quantity,
            @JsonProperty("rate") int rate) {

        this.item = item;
        this.quantity = quantity;
        this.rate = rate;
    }
}
