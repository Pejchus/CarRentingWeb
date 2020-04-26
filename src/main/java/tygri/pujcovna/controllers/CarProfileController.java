package tygri.pujcovna.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import tygri.pujcovna.model.AuthorityType;
import tygri.pujcovna.model.Car;
import tygri.pujcovna.model.User;
import tygri.pujcovna.services.CarService;
import tygri.pujcovna.services.CarorderService;
import tygri.pujcovna.services.UserService;

@Controller
public class CarProfileController {

    private final CarService carService;
    private final CarorderService carorderService;
    private final UserService userService;

    @Autowired
    public CarProfileController(CarService carService, CarorderService carorderService, UserService userService) {
        this.carService = carService;
        this.carorderService = carorderService;
        this.userService = userService;
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        mv.addObject("minDate", formatter.format(date));
        if (session.getAttribute("UserStatus") != null && session.getAttribute("UserStatus") != AuthorityType.ROLE_CUSTOMER) {
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
        mv.addObject("carId", id);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/mm/dd");
        Date date = new Date();
        mv.addObject("minDate", formatter.format(date));
        mv.addObject("disabled", "");
        return mv;
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')")
    @RequestMapping(value = "/makeOrder", method = RequestMethod.GET)
    public ModelAndView makeOrder(HttpSession session, @RequestParam String carId, @RequestParam String tripstart, @RequestParam String tripend) {
        ModelAndView mv = new ModelAndView("/carProfile.jsp");
        User user = userService.loadUserByUsername(session.getAttribute("userName").toString());
        Car car = carService.getCarById(carId);
        if (carorderService.createOrder(user, car, tripstart, tripend)) {
            mv.addObject("createOrderMsg", "<p>Order successfully made on specified date</p>");
        } else {
            mv.addObject("createOrderMsg", "<p>Unable to order for that date</p>");
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
        mv.addObject("carId", car.getId());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/mm/dd");
        Date date = new Date();
        mv.addObject("minDate", formatter.format(date));
        if (session.getAttribute("UserStatus") != AuthorityType.ROLE_CUSTOMER) {
            mv.addObject("orders", carorderService.getAllOrders(car));
            mv.addObject("disabled", "");
        } else {
            mv.addObject("disabled", "hidden");
        }
        return mv;
    }
}
