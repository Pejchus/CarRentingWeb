package tygri.pujcovna.controllers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.ModelAndView;
import tygri.pujcovna.model.UserAuthoritiesWrapper;
import tygri.pujcovna.model.User;
import tygri.pujcovna.services.CarService;
import tygri.pujcovna.services.UserService;

@SessionAttributes({"currentUser"})
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @PreAuthorize("not(hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN'))")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("/login.jsp");
    }

    @PreAuthorize("not(hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN'))")
    @RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
    public ModelAndView loginError() {
        System.out.println("Login attempt failed");
        ModelAndView mv = new ModelAndView("/login.jsp");
        mv.addObject("errorMsg", "<p>Login failed!</p>");
        return mv;
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(SessionStatus session) {
        System.out.println("Logout");
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();//clears session
        ModelAndView mv = new ModelAndView("/login.jsp");
        mv.addObject("errorMsg", "<p>You successfully logged out</p>");
        return mv;
    }

    @RequestMapping(value = "/postLogin", method = RequestMethod.POST)
    public ModelAndView postLogin(HttpSession session) {
        System.out.println("/postLogin controller called");
        // read principal out of security context and set it to session
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        User loggedInUser = ((UserAuthoritiesWrapper) authentication.getPrincipal()).getUserDetails();
        ModelAndView mv = new ModelAndView("redirect:/");
        session.setAttribute("userId", loggedInUser.getId());
        session.setAttribute("firstname", loggedInUser.getFirstname());
        session.setAttribute("lastname", loggedInUser.getLastname());
        session.setAttribute("phone", loggedInUser.getPhone());
        session.setAttribute("email", loggedInUser.getEmail());
        session.setAttribute("coountrycode", loggedInUser.getCountryCode());
        session.setAttribute("city", loggedInUser.getCity());
        session.setAttribute("street", loggedInUser.getStreet());
        session.setAttribute("streetno", loggedInUser.getStreetno());

        session.setAttribute("UserStatus", loggedInUser.getAuthorities().iterator().next());
        
        return mv;
    }

    private void validatePrinciple(Object principal) {
        if (!(principal instanceof UserAuthoritiesWrapper)) {
            throw new IllegalArgumentException("Principal can not be null!");
        }
    }
}
