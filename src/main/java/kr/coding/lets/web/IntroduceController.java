package kr.coding.lets.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.coding.lets.model.Menus;
import kr.coding.lets.model.SessionUser;
import kr.coding.lets.repository.MenusRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/intro")
@Controller
public class IntroduceController {
    @Autowired
    private MenusRepository menusRepository;

    private final HttpSession httpSession;
    @GetMapping("/")
    public ModelAndView intro(ModelAndView mv){
        List<Menus> menus = menusRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        if(menus.size() > 0){
            mv.addObject("menus", menus);
        }
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null){
            mv.addObject("userName", user.getName());
            mv.addObject("user", user);
        }
        mv.setViewName("intro/landing");
        return mv;
    }
}
