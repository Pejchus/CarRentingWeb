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
public class MainController implements ErrorController {

    private final CarService carService;
    private final UserService userService;

    @Autowired
    public MainController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public ModelAndView index(HttpSession session) {
        ModelAndView mv = new ModelAndView("/index.jsp");
        mv.addObject("carData", carService.getAllCars());
        mv.addObject("userData", userService.getAllUsers());
        mv.addObject("LoggedUser", session.getAttribute("userName"));
        if (session.getAttribute("UserStatus") == null) {
            mv.addObject("UserStatus", "Not logged in");
        } else {
            mv.addObject("UserStatus", "Logged in as: " + session.getAttribute("UserStatus"));
        }
        return mv;
    }

    @RequestMapping(value = "/addCar", method = RequestMethod.POST)
    public ModelAndView addCar(HttpSession session, @RequestParam String model, @RequestParam String brand, @RequestParam String baseprice, @RequestParam String color, @RequestParam String power, @RequestParam String productionyear, @RequestParam String trunkvolume, @RequestParam String foldingrearseats, @RequestParam String seats, @RequestParam String consumption, @RequestParam String description, @RequestParam MultipartFile photo, @RequestParam String carcategory) {
        ModelAndView mv = new ModelAndView("/index.jsp");
        if (carService.createCar(model, brand, baseprice, color, power, productionyear, trunkvolume, foldingrearseats, seats, consumption, description, photo, carcategory)) {
            mv.addObject("carAddedMessage", "<p>Car added!</p>");
        } else {
            mv.addObject("carAddedMessage", "<p>Car not added!</p>");
        }
        mv.addObject("carData", carService.getAllCars());
        mv.addObject("userData", userService.getAllUsers());
        mv.addObject("LoggedUser", session.getAttribute("userName"));
        return mv;
    }

    @RequestMapping("/addUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addUser(HttpSession session, @RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String enabled, @RequestParam String phone, @RequestParam String countryCode, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String city, @RequestParam String street, @RequestParam String streetNo, @RequestParam String authority) {
        ModelAndView mv = new ModelAndView("redirect:/");
        if (userService.createUser(username, password, email, enabled, phone, countryCode, firstname, lastname, city, street, streetNo, authority)) {
            mv.addObject("userAddedMessage", "<p>User added!</p>");
        } else {
            mv.addObject("userAddedMessage", "<p>User not added!</p>");
        }
        mv.addObject("userData", userService.getAllUsers());
        mv.addObject("carData", carService.getAllCars());
        mv.addObject("LoggedUser", session.getAttribute("userName"));
        return mv;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "error.html";
    }
}
