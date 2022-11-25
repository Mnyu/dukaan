package com.dukaan.admin.config;

import com.dukaan.admin.security.DukaanUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// WebSecurityConfigurerAdapter is deprecated now
// Reference : https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authenticationProvider(authenticationProvider());
    http.authorizeRequests()
        .antMatchers("/login").permitAll()
        .antMatchers("/users/**").hasAuthority("Admin")
        .antMatchers("/categories/**").hasAnyAuthority("Admin", "Editor")
        .antMatchers("/brands/**").hasAnyAuthority("Admin", "Editor")
        .antMatchers("/products/**").hasAnyAuthority("Admin", "Seller", "Editor", "Shipper")
        .antMatchers("/questions/**").hasAnyAuthority("Admin", "Assistant")
        .antMatchers("/reviews/**").hasAnyAuthority("Admin", "Assistant")
        .antMatchers("/customers/**").hasAnyAuthority("Admin", "Seller")
        .antMatchers("/shipping/**").hasAnyAuthority("Admin", "Seller")
        .antMatchers("/orders/**").hasAnyAuthority("Admin", "Seller", "Shipper")
        .antMatchers("/reports/**").hasAnyAuthority("Admin", "Seller")
        .antMatchers("/articles/**").hasAnyAuthority("Admin", "Editor")
        .antMatchers("/menus/**").hasAnyAuthority("Admin", "Editor")
        .antMatchers("/settings/**").hasAuthority("Admin")
        .anyRequest().authenticated();
    return http.build();
  }

//  @Bean
//  public WebSecurityCustomizer webSecurityCustomizer() {
//    return (web) -> web.ignoring().antMatchers("/js/**", "/images/**");
//  }

//  @Bean
//  public AuthTokenFilter authenticationJwtTokenFilter() {
//    return new AuthTokenFilter();
//  }

  @Bean
  public UserDetailsService userDetailsService() {
    return new DukaanUserDetailsService();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration)
      throws Exception {
    return authConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
