package com.example.JunkTrade.admin.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResponseDTO {
    private String Admintoken;
    Long AdminId;
    boolean Status=true;


}
