package kr.coding.lets.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.coding.lets.model.Menus;
import kr.coding.lets.model.SessionUser;
import kr.coding.lets.repository.MenusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
    private final HttpSession httpSession;

    @Autowired
    private MenusRepository menusRepository;
    
    @GetMapping("/signin")
    public ModelAndView signin(ModelAndView mv){
        mv.setViewName("signin");
        return mv;
    }
    @GetMapping({"/", "/index"})
    public ModelAndView index(ModelAndView mv, @AuthenticationPrincipal DefaultOAuth2User defaultUser){
        List<Menus> menus = menusRepository.findAll();
        for(Menus menu: menus){
            log.info("menu name: " + menu.getName());
        }
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            mv.addObject("userName", user.getName());
            mv.addObject("user", user);
        }
        
        if(defaultUser != null){
            mv.addObject("auth", defaultUser.getAuthorities());
            mv.addObject("oauth2", defaultUser.getAttributes());
        }
        mv.setViewName("index");
        return mv;
    }
    @GetMapping("/lets")
    public ModelAndView lets(ModelAndView mv){
        mv.setViewName("lets");
        return mv;
    }
    @GetMapping("/admin")
    public ModelAndView admin(ModelAndView mv){
        mv.setViewName("admin");
        return mv;
    }
    @GetMapping("/common")
    public ModelAndView common(ModelAndView mv){
        List<Menus> menus = menusRepository.findAll();
        for(Menus menu: menus){
            log.info("menu name: " + menu.getName());
        }
        if(menus.size() > 0){
            mv.addObject("menus", menus);
        }
        mv.setViewName("/common");
        return mv;
    }

    @GetMapping("/calc")
    public ModelAndView calc(ModelAndView mv){
        mv.setViewName("/calc");
        return mv;
    }
}
