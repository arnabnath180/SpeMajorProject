package com.example.JunkTrade.seller.services;

import com.example.JunkTrade.Repository.ScrapItemListingRepo;
import com.example.JunkTrade.Repository.ScrapListingRepo;
import com.example.JunkTrade.Repository.SellerRepo;
import com.example.JunkTrade.admin.dtos.AdminItemResponseDTO;
import com.example.JunkTrade.jwt.JwtService;
import com.example.JunkTrade.models.ScrapItemListings;
import com.example.JunkTrade.models.ScrapListings;
import com.example.JunkTrade.models.Seller;
import com.example.JunkTrade.seller.dtos.*;
import com.example.JunkTrade.util.ImageUtils;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

   private SellerRepo sellerRepo;
    @Autowired
    private JwtService jwtService;

    private ScrapListingRepo scrapListingRepo;
    private ScrapItemListingRepo scrapItemListingRepo;

    private String folderpath = "/home/arnab/junktradefrontend/public/assessts";

    public SellerService(SellerRepo sellerRepo, ScrapListingRepo scrapListingRepo, ScrapItemListingRepo scrapItemListingRepo) {
        this.sellerRepo = sellerRepo;
        this.scrapItemListingRepo = scrapItemListingRepo;
        this.scrapListingRepo = scrapListingRepo;
    }

    public Seller findSellerByEmail(String email) {

        return sellerRepo.findByEmail(email);
    }

    public SellerLoginResponseDTO verifySeller(LoginSellerDTO request) {
        Seller seller = sellerRepo.findByEmail(request.getEmail());
        if(seller == null) {
            throw new RuntimeException("Seller not found");
        }
        String token = jwtService.createSellerJwt(seller.getEmail(), seller.getRoles());
        SellerLoginResponseDTO response = new SellerLoginResponseDTO();
        response.setSellertoken(token);
        response.setSellerId(seller.getId());
        return response;
    }

    public void addSeller(SellerRegistrationDTO sellerRegistrationDTO){
        System.out.println(sellerRegistrationDTO.getPhoneNumber());

        Seller seller=new Seller(sellerRegistrationDTO.getPhoneNumber(),
                sellerRegistrationDTO.getPassword(), sellerRegistrationDTO.getFname(), sellerRegistrationDTO.getLname(),
                sellerRegistrationDTO.getEmail(), "ROLE_SELLER");
        sellerRepo.save(seller);
    }

    public Long addOrders(OrderRequestDTO orderRequestDTO, Seller seller) {
        ScrapListings scrapListings = new ScrapListings();
        int price = 0;
        scrapListings.setSeller(seller);
        scrapListings.setDistrict(orderRequestDTO.getDistrict());
        scrapListings.setScrapCategory(orderRequestDTO.getScrapCategory());
        java.sql.Time t=new java.sql.Time(orderRequestDTO.getPickupTime() *1000-(5*3600+30*60)*1000);
        scrapListings.setPickupTime(t);
        scrapListings.setPickupDate(orderRequestDTO.getPickupDate());
      //  scrapListings.setPrice(orderRequestDTO.getPrice());
        scrapListings.setAddressLine(orderRequestDTO.getAddressLine());
        scrapListings.setState(orderRequestDTO.getState());
        scrapListings.setPinCode(orderRequestDTO.getPinCode());
       // scrapListings.setImagePath("kk");
        List<ScrapItemListings> scrapItemListings = new ArrayList<>();
        for(ScrapItemListDTO i:orderRequestDTO.getScrapItemListDTOS()){
            ScrapItemListings s=new ScrapItemListings();
            s.setItem(i.getItem());
            s.setQuantity(i.getQuantity());
            s.setRate(i.getRate());
            price=price+(i.getQuantity()*i.getRate());
            scrapItemListings.add(s);
        }
        scrapListings.setPrice(price);
        scrapListings.setScrapItemListings(scrapItemListings);
        ScrapListings scrapObject = scrapListingRepo.save(scrapListings);
        for(ScrapItemListings scrap : scrapItemListings){
            scrap.setScrap_listing(scrapObject);
            scrapItemListingRepo.save(scrap);
        }
        return scrapObject.getId();
    }

    public List<MyOrdersDTO> myOrders(Seller seller) {
        List<ScrapListings> scrapListings = scrapListingRepo.findAllBySeller(seller);
        List<MyOrdersDTO> res=new ArrayList<>();
        for(ScrapListings i:scrapListings){
            MyOrdersDTO m=new MyOrdersDTO();
            m.setId(i.getId());
            m.setTime(i.getPickupTime());
            m.setPrice(i.getPrice());
            m.setPickupDate(i.getPickupDate());
            m.setCategory(i.getScrapCategory());
            res.add(m);
        }
        return res;
    }

    public List<SellerItemResponseDTO> getScrapItemList(Long scrapId) {

        List<ScrapItemListings> scrapItemListings = scrapItemListingRepo.findByScrap_listing(scrapId);
        List<SellerItemResponseDTO> sellerItemResponseDTOList=new ArrayList<>();
        for(ScrapItemListings i:scrapItemListings){
            SellerItemResponseDTO sellerItemResponseDTO=new SellerItemResponseDTO();
            sellerItemResponseDTO.setItem(i.getItem());
            sellerItemResponseDTO.setQuantity(i.getQuantity());
            sellerItemResponseDTO.setRate(i.getRate());
            sellerItemResponseDTOList.add(sellerItemResponseDTO);
        }
        return sellerItemResponseDTOList;


    }

    public String deleteScrapFromList(Long scrapId) {
        scrapListingRepo.deleteById(scrapId);
        return "records deleted successfully";
    }

/*    public String uploadScrapRecord(MultipartFile request, Long scrapId) throws IOException {
        String filename = request.getOriginalFilename().substring(0, request.getOriginalFilename().length()-4);
        filename = filename + RandomString.make(30) + ".png";
        System.out.println(filename);
        String filePath = folderpath + filename;
        ScrapListings p = scrapListingRepo.findById(scrapId).get();
        if(p == null) {
            throw new RuntimeException("Object not found");
        }
        p.setImagePath(filePath);
        ScrapListings fileData = scrapListingRepo.save(p);

        ImageUtils.compressImage(request.getBytes());
        request.transferTo(new File(filePath));

        if(fileData == null) {
            throw new RuntimeException("File not uploaded");
        }
        return "File uploaded successfully";
    }*/
}

