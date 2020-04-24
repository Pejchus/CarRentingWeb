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
