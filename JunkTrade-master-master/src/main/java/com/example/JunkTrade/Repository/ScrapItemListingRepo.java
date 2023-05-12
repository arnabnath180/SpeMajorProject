package com.example.JunkTrade.Repository;


import com.example.JunkTrade.models.ScrapItemListings;
import com.example.JunkTrade.models.ScrapListings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapItemListingRepo extends JpaRepository<ScrapItemListings,Long> {
    @Query(value = "Select * from scrap_item_listings where scrap_listing_id=?1", nativeQuery = true)
    List<ScrapItemListings> findByScrap_listing(Long id);
}
