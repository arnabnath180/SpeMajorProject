package com.example.JunkTrade.admin.controller;

import com.example.JunkTrade.admin.dtos.AdminItemResponseDTO;
import com.example.JunkTrade.admin.dtos.AdminResponseDTO;
import com.example.JunkTrade.admin.dtos.LoginAdminDTO;
import com.example.JunkTrade.admin.dtos.LoginResponseDTO;
import com.example.JunkTrade.admin.services.AdminService;
import com.example.JunkTrade.models.ScrapItemListings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/")
public class AdminController {

    Logger logger = LoggerFactory.getLogger(AdminController.class);


    @Autowired
    private AdminService adminService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> verifyPatient(
            @RequestBody LoginAdminDTO request, HttpServletRequest req)
    {   long startTime = System.currentTimeMillis();
        LoginResponseDTO verifiedAdmin = adminService.verifyAdmin(request);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String httpRequestMethod = "POST";
        String uriPath = req.getRequestURI();
        int httpStatus = HttpStatus.OK.value();

        // ... your code here ...
        String requestData = "Admin logged in successfully";
        // Calculate the response time
        long endTime = System.currentTimeMillis();
        double responseTime = (endTime - startTime) / 1000.0;

        logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);

        return ResponseEntity.ok().body(verifiedAdmin);
    }

    @DeleteMapping("/deleteScrapList/{scrapId}")
    public String deleteScrapFromList(@PathVariable Long scrapId, HttpServletRequest req) {
        long startTime = System.currentTimeMillis();
        String result = adminService.deleteScrapFromList(scrapId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String httpRequestMethod = "DELETE";
        String uriPath = req.getRequestURI();
        int httpStatus = HttpStatus.OK.value();

        // ... your code here ...
        String requestData = "ScrapList with scrapId = " + scrapId + " deleted successfully";
        // Calculate the response time
        long endTime = System.currentTimeMillis();
        double responseTime = (endTime - startTime) / 1000.0;

        logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);
        return result;
    }

    @GetMapping("/getScrapList")
    public List<AdminResponseDTO> getScrapList(HttpServletRequest req)
    {
        long startTime = System.currentTimeMillis();
        List<AdminResponseDTO> adminResponseDTOS = adminService.getScrapList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String httpRequestMethod = "GET";
        String uriPath = req.getRequestURI();
        int httpStatus = HttpStatus.OK.value();

        // ... your code here ...
        String requestData = "ScrapList fetched successfully";
        // Calculate the response time
        long endTime = System.currentTimeMillis();
        double responseTime = (endTime - startTime) / 1000.0;

        logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);
        return adminResponseDTOS;
    }

    @GetMapping("/getScrapItemList/{scrapId}")
    public List<AdminItemResponseDTO> getScrapItemList(@PathVariable Long scrapId, HttpServletRequest req){
        long startTime = System.currentTimeMillis();
        List<AdminItemResponseDTO> adminItemResponseDTOS = adminService.getScrapItemList(scrapId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String httpRequestMethod = "GET";
        String uriPath = req.getRequestURI();
        int httpStatus = HttpStatus.OK.value();

        // ... your code here ...
        String requestData = "ScrapItemList with scrapId = " + scrapId + " fetched successfully";
        // Calculate the response time
        long endTime = System.currentTimeMillis();
        double responseTime = (endTime - startTime) / 1000.0;

        logger.trace("{} GMT {} {} {} - {} ms {}", timestamp, httpRequestMethod, uriPath, httpStatus, responseTime, requestData);
        return adminItemResponseDTOS;
    }

}
