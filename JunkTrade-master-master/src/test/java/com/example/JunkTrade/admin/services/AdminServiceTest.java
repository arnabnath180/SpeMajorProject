package com.example.JunkTrade.admin.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.JunkTrade.Repository.AdminRepo;
import com.example.JunkTrade.Repository.ScrapItemListingRepo;
import com.example.JunkTrade.Repository.ScrapListingRepo;
import com.example.JunkTrade.Repository.SellerRepo;
import com.example.JunkTrade.admin.dtos.AdminItemResponseDTO;
import com.example.JunkTrade.admin.dtos.LoginAdminDTO;
import com.example.JunkTrade.admin.dtos.LoginResponseDTO;
import com.example.JunkTrade.jwt.JwtService;
import com.example.JunkTrade.models.Admin;
import com.example.JunkTrade.models.ScrapItemListings;
import com.example.JunkTrade.models.ScrapListings;
import com.example.JunkTrade.models.Seller;

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

@ContextConfiguration(classes = {AdminService.class})
@ExtendWith(SpringExtension.class)
class AdminServiceTest {
    @MockBean
    private AdminRepo adminRepo;

    @Autowired
    private AdminService adminService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private ScrapItemListingRepo scrapItemListingRepo;

    @MockBean
    private ScrapListingRepo scrapListingRepo;

    @MockBean
    private SellerRepo sellerRepo;

    /**
     * Method under test: {@link AdminService#findAdminByemail(String)}
     */
    @Test
    void testFindAdminByemail() {
        Admin admin = new Admin();
        admin.setEmail("jane.doe@example.org");
        admin.setId(1L);
        admin.setPassword("iloveyou");
        admin.setRoles("Roles");
        when(adminRepo.findByEmail(Mockito.<String>any())).thenReturn(admin);
        assertSame(admin, adminService.findAdminByemail("jane.doe@example.org"));
        verify(adminRepo).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminService#findAdminByemail(String)}
     */
    @Test
    void testFindAdminByemail2() {
        when(adminRepo.findByEmail(Mockito.<String>any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> adminService.findAdminByemail("jane.doe@example.org"));
        verify(adminRepo).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminService#verifyAdmin(LoginAdminDTO)}
     */
    @Test
    void testVerifyAdmin() {
        when(jwtService.createAdminJwt(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Create Admin Jwt");

        Admin admin = new Admin();
        admin.setEmail("jane.doe@example.org");
        admin.setId(1L);
        admin.setPassword("iloveyou");
        admin.setRoles("Roles");
        when(adminRepo.findByEmail(Mockito.<String>any())).thenReturn(admin);
        LoginResponseDTO actualVerifyAdminResult = adminService
                .verifyAdmin(new LoginAdminDTO("jane.doe@example.org", "iloveyou"));
        assertEquals(1L, actualVerifyAdminResult.getAdminId().longValue());
        assertTrue(actualVerifyAdminResult.isStatus());
        assertEquals("Create Admin Jwt", actualVerifyAdminResult.getAdmintoken());
        verify(jwtService).createAdminJwt(Mockito.<String>any(), Mockito.<String>any());
        verify(adminRepo).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminService#verifyAdmin(LoginAdminDTO)}
     */
    @Test
    void testVerifyAdmin2() {
        when(jwtService.createAdminJwt(Mockito.<String>any(), Mockito.<String>any()))
                .thenThrow(new RuntimeException("An error occurred"));

        Admin admin = new Admin();
        admin.setEmail("jane.doe@example.org");
        admin.setId(1L);
        admin.setPassword("iloveyou");
        admin.setRoles("Roles");
        when(adminRepo.findByEmail(Mockito.<String>any())).thenReturn(admin);
        assertThrows(RuntimeException.class,
                () -> adminService.verifyAdmin(new LoginAdminDTO("jane.doe@example.org", "iloveyou")));
        verify(jwtService).createAdminJwt(Mockito.<String>any(), Mockito.<String>any());
        verify(adminRepo).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminService#verifyAdmin(LoginAdminDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testVerifyAdmin3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.JunkTrade.admin.dtos.LoginAdminDTO.getEmail()" because "request" is null
        //       at com.example.JunkTrade.admin.services.AdminService.verifyAdmin(AdminService.java:49)
        //   See https://diff.blue/R013 to resolve this issue.

        when(jwtService.createAdminJwt(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Create Admin Jwt");

        Admin admin = new Admin();
        admin.setEmail("jane.doe@example.org");
        admin.setId(1L);
        admin.setPassword("iloveyou");
        admin.setRoles("Roles");
        when(adminRepo.findByEmail(Mockito.<String>any())).thenReturn(admin);
        adminService.verifyAdmin(null);
    }

    /**
     * Method under test: {@link AdminService#deleteScrapFromList(Long)}
     */
    @Test
    void testDeleteScrapFromList() {
        doNothing().when(scrapListingRepo).deleteById(Mockito.<Long>any());
        assertEquals("records deleted successfully", adminService.deleteScrapFromList(1L));
        verify(scrapListingRepo).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminService#deleteScrapFromList(Long)}
     */
    @Test
    void testDeleteScrapFromList2() {
        doThrow(new RuntimeException("An error occurred")).when(scrapListingRepo).deleteById(Mockito.<Long>any());
        assertThrows(RuntimeException.class, () -> adminService.deleteScrapFromList(1L));
        verify(scrapListingRepo).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminService#getScrapList()}
     */
    @Test
    void testGetScrapList() {
        when(scrapListingRepo.findAll()).thenReturn(new ArrayList<>());
        assertTrue(adminService.getScrapList().isEmpty());
        verify(scrapListingRepo).findAll();
    }

    /**
     * Method under test: {@link AdminService#getScrapList()}
     */
    @Test
    void testGetScrapList2() {
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

        ArrayList<ScrapListings> scrapListingsList = new ArrayList<>();
        scrapListingsList.add(scrapListings);
        when(scrapListingRepo.findAll()).thenReturn(scrapListingsList);
        assertEquals(1, adminService.getScrapList().size());
        verify(scrapListingRepo).findAll();
    }

    /**
     * Method under test: {@link AdminService#getScrapList()}
     */
    @Test
    void testGetScrapList3() {
        when(scrapListingRepo.findAll()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> adminService.getScrapList());
        verify(scrapListingRepo).findAll();
    }

    /**
     * Method under test: {@link AdminService#getScrapList()}
     */
    @Test
    void testGetScrapList4() {
        Seller seller = new Seller();
        seller.setEmail("jane.doe@example.org");
        seller.setFname("Fname");
        seller.setId(1L);
        seller.setLname("Lname");
        seller.setPassword("iloveyou");
        seller.setPhoneNumber("6625550144");
        seller.setRoles("Roles");
        seller.setScrapListings(new ArrayList<>());

        Seller seller2 = new Seller();
        seller2.setEmail("jane.doe@example.org");
        seller2.setFname("Fname");
        seller2.setId(1L);
        seller2.setLname("Lname");
        seller2.setPassword("iloveyou");
        seller2.setPhoneNumber("6625550144");
        seller2.setRoles("Roles");
        seller2.setScrapListings(new ArrayList<>());
        ScrapListings scrapListings = mock(ScrapListings.class);
        when(scrapListings.getPrice()).thenThrow(new RuntimeException("An error occurred"));
        when(scrapListings.getAddressLine()).thenThrow(new RuntimeException("An error occurred"));
        when(scrapListings.getDistrict()).thenThrow(new RuntimeException("An error occurred"));
        when(scrapListings.getPinCode()).thenThrow(new RuntimeException("An error occurred"));
        when(scrapListings.getPickupTime()).thenThrow(new RuntimeException("An error occurred"));
        when(scrapListings.getPickupDate()).thenThrow(new RuntimeException("An error occurred"));
        when(scrapListings.getSeller()).thenReturn(seller2);
        when(scrapListings.getId()).thenReturn(1L);
        doNothing().when(scrapListings).setAddressLine(Mockito.<String>any());
        doNothing().when(scrapListings).setDistrict(Mockito.<String>any());
        doNothing().when(scrapListings).setId(Mockito.<Long>any());
        doNothing().when(scrapListings).setPickupDate(Mockito.<Date>any());
        doNothing().when(scrapListings).setPickupTime(Mockito.<Time>any());
        doNothing().when(scrapListings).setPinCode(Mockito.<String>any());
        doNothing().when(scrapListings).setPrice(anyInt());
        doNothing().when(scrapListings).setScrapCategory(Mockito.<String>any());
        doNothing().when(scrapListings).setScrapItemListings(Mockito.<List<ScrapItemListings>>any());
        doNothing().when(scrapListings).setSeller(Mockito.<Seller>any());
        doNothing().when(scrapListings).setState(Mockito.<String>any());
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

        ArrayList<ScrapListings> scrapListingsList = new ArrayList<>();
        scrapListingsList.add(scrapListings);
        when(scrapListingRepo.findAll()).thenReturn(scrapListingsList);
        assertThrows(RuntimeException.class, () -> adminService.getScrapList());
        verify(scrapListingRepo).findAll();
        verify(scrapListings).getSeller();
        verify(scrapListings).getId();
        verify(scrapListings).getAddressLine();
        verify(scrapListings).setAddressLine(Mockito.<String>any());
        verify(scrapListings).setDistrict(Mockito.<String>any());
        verify(scrapListings).setId(Mockito.<Long>any());
        verify(scrapListings).setPickupDate(Mockito.<Date>any());
        verify(scrapListings).setPickupTime(Mockito.<Time>any());
        verify(scrapListings).setPinCode(Mockito.<String>any());
        verify(scrapListings).setPrice(anyInt());
        verify(scrapListings).setScrapCategory(Mockito.<String>any());
        verify(scrapListings).setScrapItemListings(Mockito.<List<ScrapItemListings>>any());
        verify(scrapListings).setSeller(Mockito.<Seller>any());
        verify(scrapListings).setState(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminService#getScrapItemList(Long)}
     */
    @Test
    void testGetScrapItemList() {
        when(scrapItemListingRepo.findByScrap_listing(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        assertTrue(adminService.getScrapItemList(1L).isEmpty());
        verify(scrapItemListingRepo).findByScrap_listing(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminService#getScrapItemList(Long)}
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
        List<AdminItemResponseDTO> actualScrapItemList = adminService.getScrapItemList(1L);
        assertEquals(1, actualScrapItemList.size());
        AdminItemResponseDTO getResult = actualScrapItemList.get(0);
        assertEquals("Item", getResult.getItem());
        assertEquals(1, getResult.getRate());
        assertEquals(1, getResult.getQuantity());
        verify(scrapItemListingRepo).findByScrap_listing(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AdminService#getScrapItemList(Long)}
     */
    @Test
    void testGetScrapItemList3() {
        when(scrapItemListingRepo.findByScrap_listing(Mockito.<Long>any()))
                .thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> adminService.getScrapItemList(1L));
        verify(scrapItemListingRepo).findByScrap_listing(Mockito.<Long>any());
    }
}

