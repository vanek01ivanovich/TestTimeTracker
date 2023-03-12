package com.example.demo.config.security;

import com.example.demo.config.token.AuthTokenFilter;
import com.example.demo.config.token.JwtAuthenticationEntryPoint;
import com.example.demo.config.token.JwtTokenProvider;
import com.example.demo.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfiguration {

    final private BCryptPasswordEncoder bCryptPasswordEncoder;
    final private JwtTokenProvider jwtTokenProvider;
    final private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    final private AuthTokenFilter authTokenFilter;
    final private JwtUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder,
                                 JwtTokenProvider jwtTokenProvider,
                                 JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                                 AuthTokenFilter authTokenFilter,
                                 JwtUserDetailsService userDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.authTokenFilter = authTokenFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                 httpBasic().disable()// off httpBasic
                .csrf().disable()     // off csrf
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)// add Exception Handler
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // without session
                .and()
                .authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .anyRequest().authenticated()//other URLS only authenticated( with token)
                .and()
                .anonymous()
                .and()
                .cors()
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
