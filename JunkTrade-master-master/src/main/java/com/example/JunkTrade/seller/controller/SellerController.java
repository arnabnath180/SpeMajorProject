package com.example.JunkTrade.seller.controller;

import com.example.JunkTrade.admin.controller.AdminController;
import com.example.JunkTrade.admin.dtos.AdminItemResponseDTO;
import com.example.JunkTrade.admin.dtos.LoginAdminDTO;
import com.example.JunkTrade.admin.dtos.LoginResponseDTO;
import com.example.JunkTrade.admin.services.AdminService;
import com.example.JunkTrade.models.Seller;
import com.example.JunkTrade.seller.dtos.*;
import com.example.JunkTrade.seller.services.SellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/seller")
public class SellerController {


    Logger logger = LoggerFactory.getLogger(SellerController.class);


    @Autowired
    private SellerService sellerService;
    @PostMapping("/login")
    public ResponseEntity<SellerLoginResponseDTO> verifyPatient(HttpServletRequest req,
                                                                @RequestBody LoginSellerDTO request)
    {   long startTime = System.currentTimeMillis();
        SellerLoginResponseDTO verifiedSeller = sellerService.verifySeller(request);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String httpRequestMethod = "POST";
        String uriPath = req.getRequestURI();
        int httpStatus = HttpStatus.OK.value();
        String requestData = "user login api with username " + request.getEmail();
        // ... your code here ...
        long endTime = System.currentTimeMillis();
        // Calculate the response time
        double responseTime = (endTime - startTime) / 1000.0;

        logger.info("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);
        return ResponseEntity.ok().body(verifiedSeller);
    }

    @PostMapping("/register_seller")
    public void addSeller(HttpServletRequest req, @RequestBody SellerRegistrationDTO sellerRegistrationDTO){
        long startTime = System.currentTimeMillis();
        sellerService.addSeller(sellerRegistrationDTO);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String httpRequestMethod = "POST";
        String uriPath = req.getRequestURI();
        int httpStatus = HttpStatus.OK.value();

        // ... your code here ...
        String requestData = "user registration api";
        // Calculate the response time
        long endTime = System.currentTimeMillis();
        double responseTime = (endTime - startTime) / 1000.0;

        logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);
    }
    @PostMapping("/add_orders")
    public Long addOrders(HttpServletRequest req, @RequestBody OrderRequestDTO orderRequestDTO,@AuthenticationPrincipal Seller seller){
        long startTime = System.currentTimeMillis();
        Long ans = sellerService.addOrders(orderRequestDTO,seller);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String httpRequestMethod = "POST";
        String uriPath = req.getRequestURI();
        int httpStatus = HttpStatus.OK.value();

        // ... your code here ...
        String requestData = "order placed successfully";
        // Calculate the response time
        long endTime = System.currentTimeMillis();
        double responseTime = (endTime - startTime) / 1000.0;

        logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);
        return ans;
    }
/*
    @PatchMapping("/add_image/{scrapId}")
    public ResponseEntity<?> uploadMedicalRecord(@RequestParam("image") MultipartFile request,
                                                 @PathVariable Long scrapId) throws IOException {
        sellerService.uploadScrapRecord(request, scrapId);
        return ResponseEntity.ok().build();
    }*/

    @GetMapping("/my_orders")
    public List<MyOrdersDTO> myOrders(HttpServletRequest req, @AuthenticationPrincipal Seller seller){
        long startTime = System.currentTimeMillis();
        List<MyOrdersDTO> myOrdersDTOS = sellerService.myOrders(seller);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String httpRequestMethod = "GET";
        String uriPath = req.getRequestURI();
        int httpStatus = HttpStatus.OK.value();

        // ... your code here ...
        String requestData = "order fetched successfully";
        // Calculate the response time
        long endTime = System.currentTimeMillis();
        double responseTime = (endTime - startTime) / 1000.0;

        logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);
        return myOrdersDTOS;
    }

    @GetMapping("/getScrapItemList/{scrapId}")
    public List<SellerItemResponseDTO> getScrapItemList(HttpServletRequest req, @PathVariable Long scrapId){
        long startTime = System.currentTimeMillis();
        List<SellerItemResponseDTO> itemResponseDTOS = sellerService.getScrapItemList(scrapId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String httpRequestMethod = "GET";
        String uriPath = req.getRequestURI();
        int httpStatus = HttpStatus.OK.value();

        // ... your code here ...
        String requestData = "order with scrapId = " + scrapId + "fetched successfully";
        // Calculate the response time
        long endTime = System.currentTimeMillis();
        double responseTime = (endTime - startTime) / 1000.0;

        logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);
        return itemResponseDTOS;
    }

    @DeleteMapping("/deleteScrapList/{scrapId}")
    public String deleteScrapFromList(@PathVariable Long scrapId, HttpServletRequest req){
        long startTime = System.currentTimeMillis();
        String result = sellerService.deleteScrapFromList(scrapId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String httpRequestMethod = "DELETE";
        String uriPath = req.getRequestURI();
        int httpStatus = HttpStatus.OK.value();

        // ... your code here ...
        String requestData = "order with scrapId = " + scrapId + "deleted successfully";
        // Calculate the response time
        long endTime = System.currentTimeMillis();
        double responseTime = (endTime - startTime) / 1000.0;

        logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);
        return result;

    }


}
