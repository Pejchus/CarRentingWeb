package tygri.pujcovna.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tygri.pujcovna.services.CarService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    private final CarService carService;

    @Autowired
    public HelloController(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/home.jsp");
        mv.addObject("variable", "Dosazena hodnota");
        mv.addObject("carData", carService.getAllCars());
        return mv;
    }

    @RequestMapping("/addCar")
    public ModelAndView addCar(@RequestParam String name, @RequestParam String description, @RequestParam String model) {
        ModelAndView mv = new ModelAndView("/home.jsp");
        if (carService.addCar(name, description, model)) {
            mv.addObject("carAddedMessage", "Car added!");
        } else {
            mv.addObject("carAddedMessage", "Car not added!");
        }
        mv.addObject("variable", "Dosazena hodnota");
        mv.addObject("carData", carService.getAllCars());
        return mv;
    }
}
