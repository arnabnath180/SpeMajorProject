package com.example.JunkTrade.Repository;

import com.example.JunkTrade.models.ScrapItemListings;
import com.example.JunkTrade.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository<Seller,Long> {

    Seller findByEmail(String email);

}
