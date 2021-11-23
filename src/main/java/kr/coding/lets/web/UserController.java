package kr.coding.lets.web;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.coding.lets.model.SessionUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
    private final HttpSession httpSession;
    
    // @Secured({"ROLE_LETS", "ROLE_ADMIN"})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public ModelAndView profileView(ModelAndView mv){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        mv.addObject("user", user);
        mv.setViewName("/user/profile");
        return mv;
    }
}
