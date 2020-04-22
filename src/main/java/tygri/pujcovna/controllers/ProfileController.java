package tygri.pujcovna.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(HttpSession session) {
        ModelAndView mv = new ModelAndView("/profile.jsp");
        mv.addObject("firstname", session.getAttribute("firstname"));
        mv.addObject("lastname", session.getAttribute("lastname"));
        mv.addObject("phone", session.getAttribute("phone"));
        mv.addObject("email", session.getAttribute("email"));
        mv.addObject("countrycode", session.getAttribute("countrycode"));
        mv.addObject("city", session.getAttribute("city"));
        mv.addObject("street", session.getAttribute("street"));
        mv.addObject("streetno", session.getAttribute("streetno"));
        mv.addObject("profilePhoto", userService.getPhoto(session.getAttribute("userName").toString()));
        return mv;
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')")
    @RequestMapping(value = "/setProfilePhoto", method = RequestMethod.POST)
    public ModelAndView profile(HttpSession session, @RequestParam MultipartFile photo) {
        ModelAndView mv = new ModelAndView("/profile.jsp");
        if (userService.setPhoto(photo, session.getAttribute("userName").toString())) {
            mv.addObject("profilePhotoChangeMsg", "<p>Profilove foto uspesne zmeneno</p>");
        } else {
            mv.addObject("profilePhotoChangeMsg", "<p>Profilove foto nebylo zmeneno</p>");
        }
        mv.addObject("firstname", session.getAttribute("firstname"));
        mv.addObject("lastname", session.getAttribute("lastname"));
        mv.addObject("phone", session.getAttribute("phone"));
        mv.addObject("email", session.getAttribute("email"));
        mv.addObject("countrycode", session.getAttribute("countrycode"));
        mv.addObject("city", session.getAttribute("city"));
        mv.addObject("street", session.getAttribute("street"));
        mv.addObject("streetno", session.getAttribute("streetno"));
        mv.addObject("profilePhoto", userService.getPhoto(session.getAttribute("userName").toString()));
        return mv;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
