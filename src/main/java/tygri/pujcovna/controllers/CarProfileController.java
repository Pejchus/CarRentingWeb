package tygri.pujcovna.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tygri.pujcovna.services.CarService;
import tygri.pujcovna.services.UserService;

@Controller
public class CarProfileController implements ErrorController {

    private final CarService carService;

    @Autowired
    public CarProfileController(CarService carService) {
        this.carService = carService;
    }


    @RequestMapping(value = "/carProfile",method = RequestMethod.GET)
    public ModelAndView carProfile(HttpSession session) {
        ModelAndView mv = new ModelAndView("/carProfile.jsp");



        return mv;
    }


    @Override
    public String getErrorPath() {
        return "/error";
    }


}
