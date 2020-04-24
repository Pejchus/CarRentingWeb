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
import tygri.pujcovna.services.CarService;
import tygri.pujcovna.services.UserService;

@Controller
public class ProfileController {

    private final UserService userService;
    private final CarService carService;

    @Autowired
    public ProfileController(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
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

    @RequestMapping(value = "/adminPage")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    public ModelAndView getAdminPage(HttpSession session) {
        ModelAndView mv = new ModelAndView("/adminPage.jsp");
        mv.addObject("carData", carService.getAllCarsPreviews());
        mv.addObject("userData", userService.getAllUsers());
        mv.addObject("LoggedUser", session.getAttribute("userName"));
        return mv;
    }

    @RequestMapping(value = "/doAddCar", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    public ModelAndView doAddCar(HttpSession session, @RequestParam("model") String model, @RequestParam("brand") String brand, @RequestParam("baseprice") String baseprice, @RequestParam("color") String color, @RequestParam("power") String power, @RequestParam("productionyear") String productionyear, @RequestParam("trunkvolume") String trunkvolume, @RequestParam("foldingrearseats") String foldingrearseats, @RequestParam("seats") String seats, @RequestParam("consumption") String consumption, @RequestParam("description") String description, @RequestParam("photo") MultipartFile photo, @RequestParam("carcategory") String carcategory) {
        ModelAndView mv = new ModelAndView("/adminPage.jsp");
        if (carService.createCar(model, brand, baseprice, color, power, productionyear, trunkvolume, foldingrearseats, seats, consumption, description, photo, carcategory)) {
            mv.addObject("carAddedMessage", "<p>Car added!</p>");
        } else {
            mv.addObject("carAddedMessage", "<p>Car not added!</p>");
        }
        mv.addObject("carData", carService.getAllCarsPreviews());
        mv.addObject("userData", userService.getAllUsers());
        mv.addObject("LoggedUser", session.getAttribute("userName"));
        return mv;
    }

    @RequestMapping("/doAddUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView doAddUser(HttpSession session, @RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String enabled, @RequestParam String phone, @RequestParam String countryCode, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String city, @RequestParam String street, @RequestParam String streetNo, @RequestParam String authority) {
        ModelAndView mv = new ModelAndView("/adminPage.jsp");
        if (userService.createUser(username, password, email, enabled, phone, countryCode, firstname, lastname, city, street, streetNo, authority)) {
            mv.addObject("userAddedMessage", "<p>User added!</p>");
        } else {
            mv.addObject("userAddedMessage", "<p>User not added!</p>");
        }
        mv.addObject("userData", userService.getAllUsers());
        mv.addObject("carData", carService.getAllCarsPreviews());
        mv.addObject("LoggedUser", session.getAttribute("userName"));
        return mv;
    }
}
