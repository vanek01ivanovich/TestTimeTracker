package com.example.demo.config.security;

import com.example.demo.config.token.AuthTokenFilter;
import com.example.demo.config.token.JwtAuthenticationEntryPoint;
import com.example.demo.config.token.JwtTokenProvider;
import com.example.demo.service.impl.JwtUserDetailsService;
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
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

/*        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/api/login","/api/signup").
                permitAll().antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll().
                anyRequest().authenticated().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/

/*        httpSecurity.
                 httpBasic()
                .and()// off httpBasic
                .csrf().disable()     // off csrf
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)// add Exception Handler
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // without session
                .and()
                .authorizeRequests()//.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/api").permitAll()
                .anyRequest().authenticated()//other URLS only authenticated( with token)
                .and()
                .cors()
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);*/

        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/login","/api/signup").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.cors();
        httpSecurity.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

/*        httpSecurity.antMatcher("/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated();*/

        return httpSecurity.build();
    }
}
