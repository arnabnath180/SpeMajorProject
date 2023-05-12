package com.example.JunkTrade.admin.controller;

import static org.mockito.Mockito.when;

import com.example.JunkTrade.admin.dtos.LoginAdminDTO;
import com.example.JunkTrade.admin.dtos.LoginResponseDTO;
import com.example.JunkTrade.admin.services.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

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

@ContextConfiguration(classes = {AdminController.class})
@ExtendWith(SpringExtension.class)
class AdminControllerTest {
    @Autowired
    private AdminController adminController;

    @MockBean
    private AdminService adminService;

    /**
     * Method under test: {@link AdminController#deleteScrapFromList(Long)}
     */
    @Test
    void testDeleteScrapFromList() throws Exception {
        when(adminService.deleteScrapFromList(Mockito.<Long>any())).thenReturn("jane.doe@example.org");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/admin/deleteScrapList/{scrapId}",
                1L);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("jane.doe@example.org"));
    }

    /**
     * Method under test: {@link AdminController#getScrapList()}
     */
    @Test
    void testGetScrapList() throws Exception {
        when(adminService.getScrapList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/getScrapList");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminController#getScrapList()}
     */
    @Test
    void testGetScrapList2() throws Exception {
        when(adminService.getScrapList()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/getScrapList");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminController#getScrapItemList(Long)}
     */
    @Test
    void testGetScrapItemList() throws Exception {
        when(adminService.getScrapItemList(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/getScrapItemList/{scrapId}",
                1L);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AdminController#getScrapItemList(Long)}
     */
    @Test
    void testGetScrapItemList2() throws Exception {
        when(adminService.getScrapItemList(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/getScrapItemList/{scrapId}",
                1L);
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

/*    *//**
     * Method under test: {@link AdminController#verifyPatient(LoginAdminDTO)}
     *//*
    @Test
    void testVerifyPatient() throws Exception {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setAdminId(1L);
        loginResponseDTO.setAdmintoken("ABC123");
        loginResponseDTO.setStatus(true);
        when(adminService.verifyAdmin(Mockito.<LoginAdminDTO>any())).thenReturn(loginResponseDTO);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/admin/login")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new LoginAdminDTO("jane.doe@example.org", "iloveyou")));
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"adminId\":1,\"admintoken\":\"ABC123\",\"status\":true}"));
    }*/
}

