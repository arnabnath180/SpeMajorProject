package com.example.JunkTrade.seller.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.JunkTrade.models.Seller;
import com.example.JunkTrade.seller.dtos.LoginSellerDTO;
import com.example.JunkTrade.seller.dtos.OrderRequestDTO;
import com.example.JunkTrade.seller.dtos.ScrapItemListDTO;
import com.example.JunkTrade.seller.dtos.SellerLoginResponseDTO;
import com.example.JunkTrade.seller.dtos.SellerRegistrationDTO;
import com.example.JunkTrade.seller.services.SellerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SellerController.class})
@ExtendWith(SpringExtension.class)
class SellerControllerTest {
    @Autowired
    private SellerController sellerController;

    @MockBean
    private SellerService sellerService;

    /**
     * Method under test: {@link SellerController#addSeller(SellerRegistrationDTO)}
     */
    @Test
    void testAddSeller() throws Exception {
        doNothing().when(sellerService).addSeller(Mockito.<SellerRegistrationDTO>any());

        SellerRegistrationDTO sellerRegistrationDTO = new SellerRegistrationDTO();
        sellerRegistrationDTO.setEmail("jane.doe@example.org");
        sellerRegistrationDTO.setFname("Fname");
        sellerRegistrationDTO.setLname("Lname");
        sellerRegistrationDTO.setPassword("iloveyou");
        sellerRegistrationDTO.setPhoneNumber("6625550144");
        String content = (new ObjectMapper()).writeValueAsString(sellerRegistrationDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/seller/register_seller")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(sellerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link SellerController#addOrders(OrderRequestDTO, Seller)}
     */
    @Test
    void testAddOrders() throws Exception {
        when(sellerService.addOrders(Mockito.<OrderRequestDTO>any(), Mockito.<Seller>any())).thenReturn(1L);

        ArrayList<ScrapItemListDTO> scrapItemListDTOS = new ArrayList<>();
        scrapItemListDTOS.add(new ScrapItemListDTO("Item", 1, 1));
        Date pickupDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO("Scrap Category", pickupDate, 1L, "42 Main St", "MD",
                "Pin Code", "District", new ArrayList<>());
        orderRequestDTO.setScrapItemListDTOS(scrapItemListDTOS);
        String content = (new ObjectMapper()).writeValueAsString(orderRequestDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/seller/add_orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(sellerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link SellerController#myOrders(Seller)}
     */
    @Test
    void testMyOrders() throws Exception {
        when(sellerService.myOrders(Mockito.<Seller>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/seller/my_orders");
        MockMvcBuilders.standaloneSetup(sellerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SellerController#myOrders(Seller)}
     */
    @Test
    void testMyOrders2() throws Exception {
        when(sellerService.myOrders(Mockito.<Seller>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/seller/my_orders");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(sellerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SellerController#getScrapItemList(Long)}
     */
    @Test
    void testGetScrapItemList() throws Exception {
        when(sellerService.getScrapItemList(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/seller/getScrapItemList/{scrapId}",
                1L);
        MockMvcBuilders.standaloneSetup(sellerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SellerController#getScrapItemList(Long)}
     */
    @Test
    void testGetScrapItemList2() throws Exception {
        when(sellerService.getScrapItemList(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/seller/getScrapItemList/{scrapId}",
                1L);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(sellerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SellerController#deleteScrapFromList(Long)}
     */
    @Test
    void testDeleteScrapFromList() throws Exception {
        when(sellerService.deleteScrapFromList(Mockito.<Long>any())).thenReturn("jane.doe@example.org");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/seller/deleteScrapList/{scrapId}",
                1L);
        MockMvcBuilders.standaloneSetup(sellerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("jane.doe@example.org"));
    }
/*
    *//**
     * Method under test: {@link SellerController#verifyPatient(LoginSellerDTO)}
     *//*
    @Test
    void testVerifyPatient() throws Exception {
        SellerLoginResponseDTO sellerLoginResponseDTO = new SellerLoginResponseDTO();
        sellerLoginResponseDTO.setSellerId(1L);
        sellerLoginResponseDTO.setSellertoken("ABC123");
        sellerLoginResponseDTO.setStatus(true);
        when(sellerService.verifySeller(Mockito.<LoginSellerDTO>any())).thenReturn(sellerLoginResponseDTO);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/seller/login")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new LoginSellerDTO("jane.doe@example.org", "iloveyou")));
        MockMvcBuilders.standaloneSetup(sellerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"sellertoken\":\"ABC123\",\"sellerId\":1,\"status\":true}"));
    }
    */


}

