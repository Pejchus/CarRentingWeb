package tygri.pujcovna.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tygri.pujcovna.services.UserService;

@Controller
public class ProfileController implements ErrorController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }


    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')")
    @RequestMapping("/profile")
    public ModelAndView profile(HttpSession session) {
        ModelAndView mv = new ModelAndView("/profile.jsp");
        mv.addObject("firstname", session.getAttribute("firstname"));
        mv.addObject("phone", session.getAttribute("phone"));
        mv.addObject("email", session.getAttribute("email"));
        mv.addObject("countrycode", session.getAttribute("countrycode"));
        mv.addObject("city", session.getAttribute("city"));
        mv.addObject("street", session.getAttribute("street"));
        mv.addObject("streetno", session.getAttribute("streetno"));


        return mv;
    }


    @Override
    public String getErrorPath() {
        return "/error";
    }


}
