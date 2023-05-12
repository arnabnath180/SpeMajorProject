package com.example.JunkTrade.jwt;

import com.example.JunkTrade.admin.services.AdminService;
import com.example.JunkTrade.seller.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private SellerService sellerService;
    private AdminService adminService;

    public AppSecurityConfig(
            JwtService jwtService,
            SellerService sellerService,
            AdminService adminService
    ) {
        jwtAuthenticationFilter = new JwtAuthenticationFilter(
                new JwtAuthenticationManager(
                        jwtService, sellerService, adminService
                )
        );

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/").permitAll()
                .antMatchers(HttpMethod.POST,"/admin/login","/seller/login","/seller/register").permitAll()
                .antMatchers(HttpMethod.PATCH,"/").permitAll()
                .antMatchers("/").authenticated()
                .antMatchers("/").authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class)
                //.addFilterBefore(doctorAuthFilter, AnonymousAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
        // super.configure(http);
    }
}
