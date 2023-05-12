package com.example.JunkTrade.jwt;

import com.example.JunkTrade.admin.services.AdminService;
import com.example.JunkTrade.models.Admin;
import com.example.JunkTrade.models.Seller;
import com.example.JunkTrade.seller.services.SellerService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


public class JwtAuthenticationManager implements AuthenticationManager {

    private JwtService jwtService;
    private SellerService sellerService;
    private AdminService adminService;


    public JwtAuthenticationManager(JwtService jwtService, SellerService sellerService, AdminService adminService) {
        this.jwtService = jwtService;
        this.sellerService = sellerService;
        this.adminService = adminService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof JwtAuthentication) {
            JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;

            String jwtString = jwtAuthentication.getCredentials();
            String role = jwtService.getRolesFromJwt(jwtString);

            if(role.equals("ROLE_SELLER")){
                String email = jwtService.getEmailFromJwt(jwtString);
                Seller seller = sellerService.findSellerByEmail(email);
                jwtAuthentication.setUser(seller);
            }
            else if(role.equals("ROLE_ADMIN")){
                String email = jwtService.getEmailFromJwt(jwtString);
                Admin admin = adminService.findAdminByemail(email);
                jwtAuthentication.setUser(admin);
            }
            return jwtAuthentication;

        }
        return null;
    }
}

// here we get the authication object
// from the authentication object we find out whether user exists or not
// initially auth object will only contain jwt string(through converter) than when it passed through authentication manager it will contain user object
