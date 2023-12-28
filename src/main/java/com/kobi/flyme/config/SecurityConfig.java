//package com.kobi.flyme.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//    final AuthenticationProvider authenticationProvider;
//    final JwtAuthenticationFilter jwtAuthFilter;
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf()
//                .disable()
//                .authorizeHttpRequests()
////                .requestMatchers("/register").hasRole("ADMIN")  // Restrict "/register" to ADMIN only
////                .requestMatchers("/**").hasAnyRole("ADMIN", "CLIENT")  // Allow both ADMIN and CLIENT to access all other URLs
////                .anyRequest()
////                .authenticated()
//                .requestMatchers("/**")
//                .permitAll()
//                .and() // i dont want to store auth state in session => disable session
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // new session for each request
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//        return httpSecurity.build();
//    }
//}