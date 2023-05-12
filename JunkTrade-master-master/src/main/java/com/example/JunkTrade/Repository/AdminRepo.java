package com.example.JunkTrade.Repository;

import com.example.JunkTrade.models.Admin;
import com.example.JunkTrade.models.ScrapItemListings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Long> {
    Admin findByEmail(String email);
}
