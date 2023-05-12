package com.example.JunkTrade.seller.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.JunkTrade.Repository.ScrapItemListingRepo;
import com.example.JunkTrade.Repository.ScrapListingRepo;
import com.example.JunkTrade.Repository.SellerRepo;
import com.example.JunkTrade.jwt.JwtService;
import com.example.JunkTrade.models.ScrapItemListings;
import com.example.JunkTrade.models.ScrapListings;
import com.example.JunkTrade.models.Seller;
import com.example.JunkTrade.seller.dtos.LoginSellerDTO;
import com.example.JunkTrade.seller.dtos.MyOrdersDTO;
import com.example.JunkTrade.seller.dtos.OrderRequestDTO;
import com.example.JunkTrade.seller.dtos.ScrapItemListDTO;
import com.example.JunkTrade.seller.dtos.SellerItemResponseDTO;
import com.example.JunkTrade.seller.dtos.SellerLoginResponseDTO;
import com.example.JunkTrade.seller.dtos.SellerRegistrationDTO;

import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SellerService.class})
@ExtendWith(SpringExtension.class)
class SellerServiceTest {
    @MockBean
    private JwtService jwtService;

    @MockBean
    private ScrapItemListingRepo scrapItemListingRepo;

    @MockBean
    private ScrapListingRepo scrapListingRepo;

    @MockBean
    private SellerRepo sellerRepo;

    @Autowired
    private SellerService sellerService;

    /**
     * Method under test: {@link SellerService#findSellerByEmail(String)}
     */
    @Test
    void testFindSellerByEmail() {
        Seller seller = new Seller();
        seller.setEmail("jane.doe@example.org");
        seller.setFname("Fname");
        seller.setId(1L);
        seller.setLname("Lname");
        seller.setPassword("iloveyou");
        seller.setPhoneNumber("6625550144");
        seller.setRoles("Roles");
        seller.setScrapListings(new ArrayList<>());
        when(sellerRepo.findByEmail(Mockito.<String>any())).thenReturn(seller);
        assertSame(seller, sellerService.findSellerByEmail("jane.doe@example.org"));
        verify(sellerRepo).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link SellerService#findSellerByEmail(String)}
     */
    @Test
    void testFindSellerByEmail2() {
        when(sellerRepo.findByEmail(Mockito.<String>any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> sellerService.findSellerByEmail("jane.doe@example.org"));
        verify(sellerRepo).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link SellerService#verifySeller(LoginSellerDTO)}
     */
    @Test
    void testVerifySeller() {
        when(jwtService.createSellerJwt(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Create Seller Jwt");

        Seller seller = new Seller();
        seller.setEmail("jane.doe@example.org");
        seller.setFname("Fname");
        seller.setId(1L);
        seller.setLname("Lname");
        seller.setPassword("iloveyou");
        seller.setPhoneNumber("6625550144");
        seller.setRoles("Roles");
        seller.setScrapListings(new ArrayList<>());
        when(sellerRepo.findByEmail(Mockito.<String>any())).thenReturn(seller);
        SellerLoginResponseDTO actualVerifySellerResult = sellerService
                .verifySeller(new LoginSellerDTO("jane.doe@example.org", "iloveyou"));
        assertEquals(1L, actualVerifySellerResult.getSellerId().longValue());
        assertTrue(actualVerifySellerResult.isStatus());
        assertEquals("Create Seller Jwt", actualVerifySellerResult.getSellertoken());
        verify(jwtService).createSellerJwt(Mockito.<String>any(), Mockito.<String>any());
        verify(sellerRepo).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link SellerService#verifySeller(LoginSellerDTO)}
     */
    @Test
    void testVerifySeller2() {
        when(jwtService.createSellerJwt(Mockito.<String>any(), Mockito.<String>any()))
                .thenThrow(new RuntimeException("An error occurred"));

        Seller seller = new Seller();
        seller.setEmail("jane.doe@example.org");
        seller.setFname("Fname");
        seller.setId(1L);
        seller.setLname("Lname");
        seller.setPassword("iloveyou");
        seller.setPhoneNumber("6625550144");
        seller.setRoles("Roles");
        seller.setScrapListings(new ArrayList<>());
        when(sellerRepo.findByEmail(Mockito.<String>any())).thenReturn(seller);
        assertThrows(RuntimeException.class,
                () -> sellerService.verifySeller(new LoginSellerDTO("jane.doe@example.org", "iloveyou")));
        verify(jwtService).createSellerJwt(Mockito.<String>any(), Mockito.<String>any());
        verify(sellerRepo).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link SellerService#verifySeller(LoginSellerDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testVerifySeller3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.JunkTrade.seller.dtos.LoginSellerDTO.getEmail()" because "request" is null
        //       at com.example.JunkTrade.seller.services.SellerService.verifySeller(SellerService.java:48)
        //   See https://diff.blue/R013 to resolve this issue.

        when(jwtService.createSellerJwt(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Create Seller Jwt");

        Seller seller = new Seller();
        seller.setEmail("jane.doe@example.org");
        seller.setFname("Fname");
        seller.setId(1L);
        seller.setLname("Lname");
        seller.setPassword("iloveyou");
        seller.setPhoneNumber("6625550144");
        seller.setRoles("Roles");
        seller.setScrapListings(new ArrayList<>());
        when(sellerRepo.findByEmail(Mockito.<String>any())).thenReturn(seller);
        sellerService.verifySeller(null);
    }

    /**
     * Method under test: {@link SellerService#addSeller(SellerRegistrationDTO)}
     */
    @Test
    void testAddSeller() {
        Seller seller = new Seller();
        seller.setEmail("jane.doe@example.org");
        seller.setFname("Fname");
        seller.setId(1L);
        seller.setLname("Lname");
        seller.setPassword("iloveyou");
        seller.setPhoneNumber("6625550144");
        seller.setRoles("Roles");
        seller.setScrapListings(new ArrayList<>());
        when(sellerRepo.save(Mockito.<Seller>any())).thenReturn(seller);
        sellerService
                .addSeller(new SellerRegistrationDTO("jane.doe@example.org", "iloveyou", "Fname", "Lname", "6625550144"));
        verify(sellerRepo).save(Mockito.<Seller>any());
    }

    /**
     * Method under test: {@link SellerService#addSeller(SellerRegistrationDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddSeller2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.JunkTrade.seller.dtos.SellerRegistrationDTO.getPhoneNumber()" because "sellerRegistrationDTO" is null
        //       at com.example.JunkTrade.seller.services.SellerService.addSeller(SellerService.java:60)
        //   See https://diff.blue/R013 to resolve this issue.

        Seller seller = new Seller();
        seller.setEmail("jane.doe@example.org");
        seller.setFname("Fname");
        seller.setId(1L);
        seller.setLname("Lname");
        seller.setPassword("iloveyou");
        seller.setPhoneNumber("6625550144");
        seller.setRoles("Roles");
        seller.setScrapListings(new ArrayList<>());
        when(sellerRepo.save(Mockito.<Seller>any())).thenReturn(seller);
        sellerService.addSeller(null);
    }

    /**
     * Method under test: {@link SellerService#addSeller(SellerRegistrationDTO)}
     */
    @Test
    void testAddSeller3() {
        when(sellerRepo.save(Mockito.<Seller>any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> sellerService
                .addSeller(new SellerRegistrationDTO("jane.doe@example.org", "iloveyou", "Fname", "Lname", "6625550144")));
        verify(sellerRepo).save(Mockito.<Seller>any());
    }

    /**
     * Method under test: {@link SellerService#addOrders(OrderRequestDTO, Seller)}
     */
    @Test
    void testAddOrders() {
        Seller seller = new Seller();
        seller.setEmail("jane.doe@example.org");
        seller.setFname("Fname");
        seller.setId(1L);
        seller.setLname("Lname");
        seller.setPassword("iloveyou");
        seller.setPhoneNumber("6625550144");
        seller.setRoles("Roles");
        seller.setScrapListings(new ArrayList<>());

        ScrapListings scrapListings = new ScrapListings();
        scrapListings.setAddressLine("42 Main St");
        scrapListings.setDistrict("District");
        scrapListings.setId(1L);
        scrapListings.setPickupDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scrapListings.setPickupTime(mock(Time.class));
        scrapListings.setPinCode("Pin Code");
        scrapListings.setPrice(1);
        scrapListings.setScrapCategory("Scrap Category");
        scrapListings.setScrapItemListings(new ArrayList<>());
        scrapListings.setSeller(seller);
        scrapListings.setState("MD");
        when(scrapListingRepo.save(Mockito.<ScrapListings>any())).thenReturn(scrapListings);
        Date pickupDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO("Scrap Category", pickupDate, 1L, "42 Main St", "MD",
                "Pin Code", "District", new ArrayList<>());

        Seller seller2 = new Seller();
        seller2.setEmail("jane.doe@example.org");
        seller2.setFname("Fname");
        seller2.setId(1L);
        seller2.setLname("Lname");
        seller2.setPassword("iloveyou");
        seller2.setPhoneNumber("6625550144");
        seller2.setRoles("Roles");
        seller2.setScrapListings(new ArrayList<>());
        assertEquals(1L, sellerService.addOrders(orderRequestDTO, seller2).longValue());
        verify(scrapListingRepo).save(Mockito.<ScrapListings>any());
    }

    /**
     * Method under test: {@link SellerService#addOrders(OrderRequestDTO, Seller)}
     */
    @Test
    void testAddOrders2() {
        Seller seller = new Seller();
        seller.setEmail("jane.doe@example.org");
        seller.setFname("Fname");
        seller.setId(1L);
        seller.setLname("Lname");
        seller.setPassword("iloveyou");
        seller.setPhoneNumber("6625550144");
        seller.setRoles("Roles");
        seller.setScrapListings(new ArrayList<>());

        ScrapListings scrapListings = new ScrapListings();
        scrapListings.setAddressLine("42 Main St");
        scrapListings.setDistrict("District");
        scrapListings.setId(1L);
        scrapListings
                .setPickupDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scrapListings.setPickupTime(mock(Time.class));
        scrapListings.setPinCode("Pin Code");
        scrapListings.setPrice(1);
        scrapListings.setScrapCategory("Scrap Category");
        scrapListings.setScrapItemListings(new ArrayList<>());
        scrapListings.setSeller(seller);
        scrapListings.setState("MD");
        when(scrapListingRepo.save(Mockito.<ScrapListings>any())).thenReturn(scrapListings);

        Seller seller2 = new Seller();
        seller2.setEmail("jane.doe@example.org");
        seller2.setFname("Fname");
        seller2.setId(1L);
        seller2.setLname("Lname");
        seller2.setPassword("iloveyou");
        seller2.setPhoneNumber("6625550144");
        seller2.setRoles("Roles");
        seller2.setScrapListings(new ArrayList<>());

        ScrapListings scrap_listing = new ScrapListings();
        scrap_listing.setAddressLine("42 Main St");
        scrap_listing.setDistrict("District");
        scrap_listing.setId(1L);
        scrap_listing
                .setPickupDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scrap_listing.setPickupTime(mock(Time.class));
        scrap_listing.setPinCode("Pin Code");
        scrap_listing.setPrice(1);
        scrap_listing.setScrapCategory("Scrap Category");
        scrap_listing.setScrapItemListings(new ArrayList<>());
        scrap_listing.setSeller(seller2);
        scrap_listing.setState("MD");

        ScrapItemListings scrapItemListings = new ScrapItemListings();
        scrapItemListings.setId(1L);
        scrapItemListings.setItem("Item");
        scrapItemListings.setQuantity(1);
        scrapItemListings.setRate(1);
        scrapItemListings.setScrap_listing(scrap_listing);
        when(scrapItemListingRepo.save(Mockito.<ScrapItemListings>any())).thenReturn(scrapItemListings);

        ArrayList<ScrapItemListDTO> scrapItemListDTOS = new ArrayList<>();
        scrapItemListDTOS.add(new ScrapItemListDTO("Item", 1000, 1000));
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO("Scrap Category",
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), 1L, "42 Main St", "MD",
                "Pin Code", "District", scrapItemListDTOS);

        Seller seller3 = new Seller();
        seller3.setEmail("jane.doe@example.org");
        seller3.setFname("Fname");
        seller3.setId(1L);
        seller3.setLname("Lname");
        seller3.setPassword("iloveyou");
        seller3.setPhoneNumber("6625550144");
        seller3.setRoles("Roles");
        seller3.setScrapListings(new ArrayList<>());
        assertEquals(1L, sellerService.addOrders(orderRequestDTO, seller3).longValue());
        verify(scrapListingRepo).save(Mockito.<ScrapListings>any());
        verify(scrapItemListingRepo).save(Mockito.<ScrapItemListings>any());
    }

    /**
     * Method under test: {@link SellerService#myOrders(Seller)}
     */
    @Test
    void testMyOrders() {
        when(scrapListingRepo.findAllBySeller(Mockito.<Seller>any())).thenReturn(new ArrayList<>());

        Seller seller = new Seller();
        seller.setEmail("jane.doe@example.org");
        seller.setFname("Fname");
        seller.setId(1L);
        seller.setLname("Lname");
        seller.setPassword("iloveyou");
        seller.setPhoneNumber("6625550144");
        seller.setRoles("Roles");
        seller.setScrapListings(new ArrayList<>());
        assertTrue(sellerService.myOrders(seller).isEmpty());
        verify(scrapListingRepo).findAllBySeller(Mockito.<Seller>any());
    }

    /**
     * Method under test: {@link SellerService#myOrders(Seller)}
     */
    @Test
    void testMyOrders2() {
        Seller seller = new Seller();
        seller.setEmail("jane.doe@example.org");
        seller.setFname("Fname");
        seller.setId(1L);
        seller.setLname("Lname");
        seller.setPassword("iloveyou");
        seller.setPhoneNumber("6625550144");
        seller.setRoles("Roles");
        seller.setScrapListings(new ArrayList<>());

        ScrapListings scrapListings = new ScrapListings();
        scrapListings.setAddressLine("42 Main St");
        scrapListings.setDistrict("District");
        scrapListings.setId(1L);
        Date pickupDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        scrapListings.setPickupDate(pickupDate);
        scrapListings.setPickupTime(mock(Time.class));
        scrapListings.setPinCode("Pin Code");
        scrapListings.setPrice(1);
        scrapListings.setScrapCategory("Scrap Category");
        scrapListings.setScrapItemListings(new ArrayList<>());
        scrapListings.setSeller(seller);
        scrapListings.setState("MD");

        ArrayList<ScrapListings> scrapListingsList = new ArrayList<>();
        scrapListingsList.add(scrapListings);
        when(scrapListingRepo.findAllBySeller(Mockito.<Seller>any())).thenReturn(scrapListingsList);

        Seller seller2 = new Seller();
        seller2.setEmail("jane.doe@example.org");
        seller2.setFname("Fname");
        seller2.setId(1L);
        seller2.setLname("Lname");
        seller2.setPassword("iloveyou");
        seller2.setPhoneNumber("6625550144");
        seller2.setRoles("Roles");
        seller2.setScrapListings(new ArrayList<>());
        List<MyOrdersDTO> actualMyOrdersResult = sellerService.myOrders(seller2);
        assertEquals(1, actualMyOrdersResult.size());
        MyOrdersDTO getResult = actualMyOrdersResult.get(0);
        assertEquals("Scrap Category", getResult.getCategory());
        assertEquals(1, getResult.getPrice());
        assertSame(pickupDate, getResult.getPickupDate());
        assertEquals(1L, getResult.getId().longValue());
        verify(scrapListingRepo).findAllBySeller(Mockito.<Seller>any());
    }

    /**
     * Method under test: {@link SellerService#myOrders(Seller)}
     */
    @Test
    void testMyOrders3() {
        when(scrapListingRepo.findAllBySeller(Mockito.<Seller>any()))
                .thenThrow(new RuntimeException("An error occurred"));

        Seller seller = new Seller();
        seller.setEmail("jane.doe@example.org");
        seller.setFname("Fname");
        seller.setId(1L);
        seller.setLname("Lname");
        seller.setPassword("iloveyou");
        seller.setPhoneNumber("6625550144");
        seller.setRoles("Roles");
        seller.setScrapListings(new ArrayList<>());
        assertThrows(RuntimeException.class, () -> sellerService.myOrders(seller));
        verify(scrapListingRepo).findAllBySeller(Mockito.<Seller>any());
    }

    /**
     * Method under test: {@link SellerService#getScrapItemList(Long)}
     */
    @Test
    void testGetScrapItemList() {
        when(scrapItemListingRepo.findByScrap_listing(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        assertTrue(sellerService.getScrapItemList(1L).isEmpty());
        verify(scrapItemListingRepo).findByScrap_listing(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link SellerService#getScrapItemList(Long)}
     */
    @Test
    void testGetScrapItemList2() {
        Seller seller = new Seller();
        seller.setEmail("jane.doe@example.org");
        seller.setFname("Fname");
        seller.setId(1L);
        seller.setLname("Lname");
        seller.setPassword("iloveyou");
        seller.setPhoneNumber("6625550144");
        seller.setRoles("Roles");
        seller.setScrapListings(new ArrayList<>());

        ScrapListings scrap_listing = new ScrapListings();
        scrap_listing.setAddressLine("42 Main St");
        scrap_listing.setDistrict("District");
        scrap_listing.setId(1L);
        scrap_listing
                .setPickupDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        scrap_listing.setPickupTime(mock(Time.class));
        scrap_listing.setPinCode("Pin Code");
        scrap_listing.setPrice(1);
        scrap_listing.setScrapCategory("Scrap Category");
        scrap_listing.setScrapItemListings(new ArrayList<>());
        scrap_listing.setSeller(seller);
        scrap_listing.setState("MD");

        ScrapItemListings scrapItemListings = new ScrapItemListings();
        scrapItemListings.setId(1L);
        scrapItemListings.setItem("Item");
        scrapItemListings.setQuantity(1);
        scrapItemListings.setRate(1);
        scrapItemListings.setScrap_listing(scrap_listing);

        ArrayList<ScrapItemListings> scrapItemListingsList = new ArrayList<>();
        scrapItemListingsList.add(scrapItemListings);
        when(scrapItemListingRepo.findByScrap_listing(Mockito.<Long>any())).thenReturn(scrapItemListingsList);
        List<SellerItemResponseDTO> actualScrapItemList = sellerService.getScrapItemList(1L);
        assertEquals(1, actualScrapItemList.size());
        SellerItemResponseDTO getResult = actualScrapItemList.get(0);
        assertEquals("Item", getResult.getItem());
        assertEquals(1, getResult.getRate());
        assertEquals(1, getResult.getQuantity());
        verify(scrapItemListingRepo).findByScrap_listing(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link SellerService#getScrapItemList(Long)}
     */
    @Test
    void testGetScrapItemList3() {
        when(scrapItemListingRepo.findByScrap_listing(Mockito.<Long>any()))
                .thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> sellerService.getScrapItemList(1L));
        verify(scrapItemListingRepo).findByScrap_listing(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link SellerService#deleteScrapFromList(Long)}
     */
    @Test
    void testDeleteScrapFromList() {
        doNothing().when(scrapListingRepo).deleteById(Mockito.<Long>any());
        assertEquals("records deleted successfully", sellerService.deleteScrapFromList(1L));
        verify(scrapListingRepo).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link SellerService#deleteScrapFromList(Long)}
     */
    @Test
    void testDeleteScrapFromList2() {
        doThrow(new RuntimeException("An error occurred")).when(scrapListingRepo).deleteById(Mockito.<Long>any());
        assertThrows(RuntimeException.class, () -> sellerService.deleteScrapFromList(1L));
        verify(scrapListingRepo).deleteById(Mockito.<Long>any());
    }
}

