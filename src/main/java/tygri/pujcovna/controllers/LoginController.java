package tygri.pujcovna.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import tygri.pujcovna.model.User;
import tygri.pujcovna.services.UserService;

@SessionAttributes({"currentUser"})
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @PreAuthorize("not(hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN'))")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("/login.jsp");
    }

    @PreAuthorize("not(hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN'))")
    @RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
    public ModelAndView loginError(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/login.jsp");
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        mv.addObject("errorMsg", errorMessage);
        return mv;
    }

    @PreAuthorize("not(hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN'))")
    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public ModelAndView signUp() {
        ModelAndView mv = new ModelAndView("/signup.jsp");
        return mv;
    }

    @PreAuthorize("not(hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN'))")
    @RequestMapping(value = "/doSignUp", method = RequestMethod.GET)
    public ModelAndView signUpFinish(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String phone, @RequestParam String countryCode, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String city, @RequestParam String street, @RequestParam String streetNo) {
        ModelAndView mv;
        String message;
        if (((message = userService.isOK(username, email, phone, countryCode, firstname, lastname, city, street, streetNo)) != null) || (message = userService.isUnique(username, email, phone)) != null) {
            mv = new ModelAndView("/signup.jsp");
            mv.addObject("registerMessage", message);
        } else if (userService.createUser(username, password, email, "true", phone, countryCode, firstname, lastname, city, street, streetNo, "CUSTOMER") != null) {
            mv = new ModelAndView("/login.jsp");
            mv.addObject("errorMsg", "Vas ucet byl vytvoren, muzete se prihlasit");
        } else {
            mv = new ModelAndView("/signup.jsp");
            mv.addObject("registerMessage", "<p>Ucet se nepodarilo vytvorit</p>");
        }
        return mv;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(SessionStatus session) {
        System.out.println("Logout");
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();//clears session
        ModelAndView mv = new ModelAndView("redirect:/");
        return mv;
    }

    @RequestMapping(value = "/postLogin", method = RequestMethod.POST)
    public ModelAndView postLogin(HttpSession session) {
        System.out.println("/postLogin controller called");
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        User loggedInUser = (User) authentication.getPrincipal();
        ModelAndView mv = new ModelAndView("redirect:/");
        session.setAttribute("userId", loggedInUser.getId());
        session.setAttribute("userName", loggedInUser.getUsername());
        session.setAttribute("firstname", loggedInUser.getFirstname());
        session.setAttribute("lastname", loggedInUser.getLastname());
        session.setAttribute("phone", loggedInUser.getPhone());
        session.setAttribute("email", loggedInUser.getEmail());
        session.setAttribute("coountrycode", loggedInUser.getCountryCode());
        session.setAttribute("city", loggedInUser.getCity());
        session.setAttribute("street", loggedInUser.getStreet());
        session.setAttribute("streetno", loggedInUser.getStreetno());
        session.setAttribute("UserStatus", loggedInUser.getUserAuthorities().iterator().next().getName());
        return mv;
    }

    private void validatePrinciple(Object principal) {
        if (!(principal instanceof User)) {
            throw new IllegalArgumentException("Principal can not be null!");
        }
    }
}
