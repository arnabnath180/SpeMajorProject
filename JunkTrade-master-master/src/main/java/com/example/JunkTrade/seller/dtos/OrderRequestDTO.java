package com.example.JunkTrade.seller.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderRequestDTO {
     private String scrapCategory;
     private Date pickupDate;
     private long pickupTime;
     private String addressLine;
     private String state;
     private String district;
     private String pinCode;
     private List<ScrapItemListDTO> scrapItemListDTOS;


     public OrderRequestDTO(
             @JsonProperty("scrapCategory") String scrapCategory,
             @JsonProperty("pickupDate") Date pickupDate,
             @JsonProperty("pickupTime") long pickupTime,
             @JsonProperty("addressLine") String addressLine,
             @JsonProperty("state") String state,
             @JsonProperty("pinCode") String pinCode,
             @JsonProperty("district") String district,
             @JsonProperty("scrapItemList") List<ScrapItemListDTO> scrapItemListDTOS) {
          this.scrapCategory = scrapCategory;
          this.pickupDate = pickupDate;
          this.pickupTime = pickupTime;
          this.addressLine = addressLine;
          this.state = state;
          this.district = district;
          this.pinCode = pinCode;
          this.scrapItemListDTOS = scrapItemListDTOS;
     }
}
