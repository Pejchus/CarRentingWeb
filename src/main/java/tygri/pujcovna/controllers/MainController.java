package tygri.pujcovna.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tygri.pujcovna.services.CarService;

@Controller
public class MainController implements ErrorController {

    private final CarService carService;

    @Autowired
    public MainController(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping("/")
    public ModelAndView index(HttpSession session) {
        ModelAndView mv = new ModelAndView("/index.jsp");
        mv.addObject("frontPageCars", carService.getCarsOnFrontPagePreviews());
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
