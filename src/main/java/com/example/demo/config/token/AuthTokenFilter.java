package com.example.demo.config.token;

import com.example.demo.service.impl.JwtUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {

    final private JwtTokenProvider jwtTokenProvider;
    final private JwtUserDetailsService userDetailsService;

    @Autowired
    public AuthTokenFilter(JwtTokenProvider jwtTokenProvider, JwtUserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("doFilterInternal request {}", request.getHeader("Authorization"));
        try {
            String jwtToken = jwtTokenProvider.parseToken(request);
            log.info("jwtToken {}", jwtToken);
            if (StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
                String userNameFromJwtToken = jwtTokenProvider.getUserNameFromJwtToken(jwtToken);
                if (StringUtils.hasText(userNameFromJwtToken)) {
                    throw new UsernameNotFoundException("User not found with such username");
                }
                UserDetails userDetails = userDetailsService.loadUserByUsername(userNameFromJwtToken);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            log.error("Could not set user authentication in security context ", ex);
        }

        filterChain.doFilter(request, response);
    }
}

/*After this, everytime you want to get UserDetails, just use SecurityContext like this:
* UserDetails userDetails =
	(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();*/
