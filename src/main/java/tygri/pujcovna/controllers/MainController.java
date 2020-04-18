package tygri.pujcovna.controllers;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tygri.pujcovna.services.CarService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import tygri.pujcovna.model.Car;
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
        return mv;
    }

    @RequestMapping(value = "/addCar", method = RequestMethod.POST)
    public ModelAndView addCar(HttpSession session, @RequestParam String model, @RequestParam String brand, @RequestParam String baseprice, @RequestParam String color, @RequestParam String power, @RequestParam String productionyear, @RequestParam String trunkvolume, @RequestParam String foldingrearseats, @RequestParam String seats, @RequestParam String consumption, @RequestParam String description, @RequestParam MultipartFile photo) {
        ModelAndView mv = new ModelAndView("/index.jsp");
        System.out.println(model);
        System.out.println(brand);
        System.out.println(baseprice);
        System.out.println(color);
        System.out.println(power);
        System.out.println(productionyear);
        System.out.println(trunkvolume);
        System.out.println(foldingrearseats);
        System.out.println(seats);
        System.out.println(consumption);
        System.out.println(description);
        System.out.println("photo");
        try {
            System.out.println(Arrays.toString(photo.getBytes()));
        } catch (IOException e) {
            System.out.println("photo problem");
        }
        System.out.println("phote end");
        if (carService.createCar(model, brand, baseprice, color, power, productionyear, trunkvolume, foldingrearseats, seats, consumption, description, photo)) {
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
    public ModelAndView addUser(HttpSession session, @RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String enabled, @RequestParam String phone, @RequestParam String countryCode, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String city, @RequestParam String street, @RequestParam String streetNo, @RequestParam String authority) {
        ModelAndView mv = new ModelAndView("/index.jsp");
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
