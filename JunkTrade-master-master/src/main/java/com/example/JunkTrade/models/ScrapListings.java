package com.example.JunkTrade.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name="scrap_listings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScrapListings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private java.sql.Time pickupTime;

    @Column(nullable = false)
    private Date pickupDate;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String addressLine;

    @Column(nullable = false)
    private String scrapCategory;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String pinCode;

/*
    @Column(nullable = true)
    private String imagePath;
*/

    //TODO: Forign key pointig to doctor table
    @ManyToOne
    @JoinColumn(name="seller_id")
    private Seller seller;

    //TODO: Forign key pointig to patient table
    @OneToMany(mappedBy = "scrap_listing", cascade = {CascadeType.REMOVE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ScrapItemListings> scrapItemListings;
}
