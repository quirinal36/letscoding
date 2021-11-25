package kr.coding.lets.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import kr.coding.lets.model.enums.UserRole;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        final String GUEST_ROLE = new StringBuilder("ROLE_").append(UserRole.GUEST.name().toUpperCase()).toString();
        final String LETS_ROLE = new StringBuilder("ROLE_").append(UserRole.LETS.name().toUpperCase()).toString();
        final String ADMIN_ROLE = new StringBuilder("ROLE_").append(UserRole.ADMIN.name().toUpperCase()).toString();
        
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(ADMIN_ROLE))){
            log.info("Hello Admin");
            response.sendRedirect("/admin");
        }else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(LETS_ROLE))){
            log.info("Hello Lets");
            response.sendRedirect("/lets");
        }else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(GUEST_ROLE))){
            log.info("Hello Guest");
            response.sendRedirect("/");
        }
        
    }
    
}
