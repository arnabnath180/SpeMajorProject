package com.example.JunkTrade.Repository;

import com.example.JunkTrade.models.ScrapItemListings;
import com.example.JunkTrade.models.ScrapListings;
import com.example.JunkTrade.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapListingRepo extends JpaRepository<ScrapListings,Long> {
    List<ScrapListings> findAllBySeller(Seller seller);
}
