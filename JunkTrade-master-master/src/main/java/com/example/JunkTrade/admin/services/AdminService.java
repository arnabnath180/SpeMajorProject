package com.example.JunkTrade.admin.services;

import com.example.JunkTrade.Repository.AdminRepo;
import com.example.JunkTrade.Repository.ScrapItemListingRepo;
import com.example.JunkTrade.Repository.ScrapListingRepo;
import com.example.JunkTrade.Repository.SellerRepo;
import com.example.JunkTrade.admin.dtos.AdminItemResponseDTO;
import com.example.JunkTrade.admin.dtos.AdminResponseDTO;
import com.example.JunkTrade.admin.dtos.LoginAdminDTO;
import com.example.JunkTrade.admin.dtos.LoginResponseDTO;
import com.example.JunkTrade.jwt.JwtService;
import com.example.JunkTrade.models.Admin;
import com.example.JunkTrade.models.ScrapItemListings;
import com.example.JunkTrade.models.ScrapListings;
import com.example.JunkTrade.models.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    private AdminRepo adminRepo;

    @Autowired
    private JwtService jwtService;

    private ScrapListingRepo scrapListingRepo;

    private ScrapItemListingRepo scrapItemListingRepo;

    private SellerRepo sellerRepo;

    public AdminService(SellerRepo sellerRepo,AdminRepo adminRepo, ScrapListingRepo scrapListingRepo, ScrapItemListingRepo scrapItemListingRepo) {
        this.scrapListingRepo = scrapListingRepo;
        this.scrapItemListingRepo = scrapItemListingRepo;
        this.adminRepo = adminRepo;
        this.sellerRepo = sellerRepo;
    }

    public Admin findAdminByemail(String email) {
        return adminRepo.findByEmail(email);
    }

    public LoginResponseDTO verifyAdmin(LoginAdminDTO request) {
        Admin admin = adminRepo.findByEmail(request.getEmail());
        if(admin == null) {
            throw new RuntimeException("Admin not found");
        }
        String token = jwtService.createAdminJwt(admin.getEmail(), admin.getRoles());
        LoginResponseDTO response = new LoginResponseDTO();
        response.setAdmintoken(token);
        response.setAdminId(admin.getId());
        return response;
    }

    public String deleteScrapFromList(Long scrapId) {
        scrapListingRepo.deleteById(scrapId);
        return "records deleted successfully";
    }

    public List<AdminResponseDTO> getScrapList() {
        List<ScrapListings> scrapListings = scrapListingRepo.findAll();
        List<AdminResponseDTO> adminResponseDTOS=new ArrayList<>();
        for(ScrapListings listings : scrapListings){
            AdminResponseDTO adminResponseDTO = new AdminResponseDTO();
            Seller seller = listings.getSeller();
            adminResponseDTO.setId(listings.getId());
            adminResponseDTO.setEmail(seller.getEmail());
            adminResponseDTO.setLname(seller.getLname());
            adminResponseDTO.setFname(seller.getFname());
            adminResponseDTO.setPhoneNumber(seller.getPhoneNumber());
            String address=listings.getAddressLine()+","+listings.getDistrict()+","+listings.getPinCode();
            adminResponseDTO.setAddress(address);
            adminResponseDTO.setPrice(listings.getPrice());
            adminResponseDTO.setPickupDate(listings.getPickupDate());
            adminResponseDTO.setTime(listings.getPickupTime());
            adminResponseDTOS.add(adminResponseDTO);
        }
        return adminResponseDTOS;
    }

    public List<AdminItemResponseDTO> getScrapItemList(Long scrapId) {
      List<ScrapItemListings> scrapItemListings = scrapItemListingRepo.findByScrap_listing(scrapId);
      List<AdminItemResponseDTO> adminItemResponseDTOList=new ArrayList<>();
      for(ScrapItemListings i:scrapItemListings){
          AdminItemResponseDTO adminItemResponseDTO=new AdminItemResponseDTO();
          adminItemResponseDTO.setItem(i.getItem());
          adminItemResponseDTO.setQuantity(i.getQuantity());
          adminItemResponseDTO.setRate(i.getRate());
          adminItemResponseDTOList.add(adminItemResponseDTO);
      }
      return adminItemResponseDTOList;
    }
}
