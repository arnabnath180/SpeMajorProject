package com.example.JunkTrade.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name="scrap_item_listings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScrapItemListings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String item;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int rate;

    //TODO: Forign key pointig to doctor table
    @ManyToOne
    @JoinColumn(name="scrap_listing_id")
    private ScrapListings scrap_listing;

}
