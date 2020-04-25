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
import tygri.pujcovna.model.Car;
import tygri.pujcovna.services.CarService;
import tygri.pujcovna.services.CarorderService;

@Controller
public class CarProfileController implements ErrorController {

    private final CarService carService;
    private final CarorderService carorderService;

    @Autowired
    public CarProfileController(CarService carService, CarorderService carorderService) {
        this.carService = carService;
        this.carorderService = carorderService;
    }

    @RequestMapping(value = "/carProfile", method = RequestMethod.GET)
    public ModelAndView carProfile(HttpSession session, @RequestParam String id) {
        ModelAndView mv = new ModelAndView("/carProfile.jsp");
        Car car = carService.getCarById(id);
        mv.addObject("brand", car.getBrand());
        mv.addObject("name", car.getModel());
        mv.addObject("foto", carService.getPhoto(car));
        mv.addObject("productionyear", car.getProductionyear());
        mv.addObject("seats", car.getSeats());
        mv.addObject("consumption", car.getConsumption());
        mv.addObject("power", car.getPower());
        mv.addObject("baseprice", car.getBaseprice());
        mv.addObject("description", car.getDescription());
        mv.addObject("carId", id);
        if (session.getAttribute("UserStatus") != null && session.getAttribute("UserStatus") != "Customer") {
            mv.addObject("orders", carorderService.getAllOrders(car));
            mv.addObject("disabled", "");
        } else {
            mv.addObject("disabled", "hidden");
        }
        return mv;
    }

    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    @RequestMapping(value = "/setCarProfilePhoto", method = RequestMethod.POST)
    public ModelAndView setCarProfilePhoto(HttpSession session, @RequestParam("photo") MultipartFile photo, @RequestParam("carid") String id) {
        ModelAndView mv = new ModelAndView("/carProfile.jsp");
        Car car = carService.getCarById(id);
        if (carService.setPhoto(car, photo)) {
            mv.addObject("carProfilePhotoChangeMsg", "<p>Car photo changed!</p>");
        } else {
            mv.addObject("carProfilePhotoChangeMsg", "<p>Car photo NOT changed!</p>");
        }
        mv.addObject("brand", car.getBrand());
        mv.addObject("name", car.getModel());
        mv.addObject("foto", carService.getPhoto(car));
        mv.addObject("productionyear", car.getProductionyear());
        mv.addObject("seats", car.getSeats());
        mv.addObject("consumption", car.getConsumption());
        mv.addObject("power", car.getPower());
        mv.addObject("baseprice", car.getBaseprice());
        mv.addObject("description", car.getDescription());
        mv.addObject("orders", carorderService.getAllOrders(car));
        mv.addObject("disabled", "");
        return mv;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
