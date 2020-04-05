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

    //ukazka
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/home.jsp");
        mv.addObject("variable", "Dosazena hodnota");
        mv.addObject("carData", carService.getAllCars());
        return mv;
    }

    //ukazka
    @RequestMapping("/addCar")
    public ModelAndView addCar(@RequestParam String model, @RequestParam String brand, @RequestParam String baseprice, @RequestParam String color, @RequestParam String power, @RequestParam String productionyear, @RequestParam String trunkvolume, @RequestParam String foldingrearseats, @RequestParam String seats, @RequestParam String consumption, @RequestParam String description) {
        ModelAndView mv = new ModelAndView("/home.jsp");
        if (carService.createCar(model, brand, baseprice, color, power, productionyear, trunkvolume, foldingrearseats, seats, consumption, description)) {
            mv.addObject("carAddedMessage", "Car added!");
        } else {
            mv.addObject("carAddedMessage", "Car not added!");
        }
        mv.addObject("variable", "Dosazena hodnota");
        mv.addObject("carData", carService.getAllCars());
        return mv;
    }
}
