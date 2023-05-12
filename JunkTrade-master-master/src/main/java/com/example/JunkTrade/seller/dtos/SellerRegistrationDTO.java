package com.example.JunkTrade.seller.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@NoArgsConstructor
public class SellerRegistrationDTO {
    private String email;
    private String password;
    private String fname;
    private String lname;
    private String phoneNumber;

    public SellerRegistrationDTO(@JsonProperty("email") String email,
                                 @JsonProperty("password") String password,
                                 @JsonProperty("fname") String fname,
                                 @JsonProperty("lname") String lname,
                                 @JsonProperty("phoneNumber") String phoneNumber) {
        this.email = email;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
