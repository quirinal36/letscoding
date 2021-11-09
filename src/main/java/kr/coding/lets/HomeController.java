package kr.coding.lets;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.coding.lets.model.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
    private final HttpSession httpSession;
    
    @GetMapping("/")
    public ModelAndView index(ModelAndView mv, @AuthenticationPrincipal DefaultOAuth2User defaultUser){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            mv.addObject("userName", user.getName());
            mv.addObject("user", user);
        }
        
        if(defaultUser != null){
            log.info("user name: "+defaultUser.getName());
            
            mv.addObject("auth", defaultUser.getAuthorities());
            mv.addObject("oauth2", defaultUser.getAttributes());
        }
        mv.setViewName("index");
        return mv;
    }
}
