package com.example.JunkTrade.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity(name="seller")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="seller_id")
    private Long id;
    @Column(name="email", unique = true, nullable = false)
    private String email; // userId of patient

    @Column(nullable = false)
    private String password;


    @Column(nullable = false, unique = false)
    private String fname;


    private String lname;

    @Column(name="phone_number", unique = true, nullable = false)
    private String phoneNumber;


    @Column(name = "roles",nullable = false)
    private String roles="ROLE_SELLER";

    @OneToMany(mappedBy = "seller", cascade = {CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ScrapListings> scrapListings;


    public Seller(
            @JsonProperty("phoneNumber")String phoneNumber,
            @JsonProperty("password")String password,
            @JsonProperty("fname")String fname,
            @JsonProperty("lname")String lname,
            @JsonProperty("email")String email,
            @JsonProperty("roles")String roles) {

        this.phoneNumber = phoneNumber;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.roles = roles;
    }

    public Seller(Long id) {
        this.id = id;
    }
}


