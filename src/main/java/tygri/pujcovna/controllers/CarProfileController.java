package tygri.pujcovna.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import tygri.pujcovna.model.AuthorityType;
import tygri.pujcovna.model.Car;
import tygri.pujcovna.model.Carorder;
import tygri.pujcovna.model.User;
import tygri.pujcovna.services.CarService;
import tygri.pujcovna.services.CarorderService;
import tygri.pujcovna.services.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
        setCommonCarProfileVariables(mv, session, car);
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
        setCommonCarProfileVariables(mv, session, car);
        return mv;
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')")
    @RequestMapping(value = "/makeOrder", method = RequestMethod.GET)
    public ModelAndView makeOrder(HttpSession session, @RequestParam String carId, @RequestParam String tripstart, @RequestParam String tripend, @RequestParam(required = false) String username) {
        ModelAndView mv = new ModelAndView("/carProfile.jsp");
        User user = userService.loadUserByUsername(session.getAttribute("userName").toString());
        Car car = carService.getCarById(carId);
        if (!"".equals(tripstart) && !"".equals(tripend)) {
            String[] tripStart = tripstart.split("/");
            tripstart = tripStart[2] + "-" + tripStart[0] + "-" + tripStart[1];
            String[] tripEnd = tripend.split("/");
            tripend = tripEnd[2] + "-" + tripEnd[0] + "-" + tripEnd[1];
            boolean userok = true;
            if (null != username) {
                try {
                    user = userService.loadUserByUsername(username);
                } catch (UsernameNotFoundException e) {
                    userok = false;
                    mv.addObject("createOrderMsg", "<p>Please fill valid username</p>");
                }
            }
            if (userok && carorderService.createOrder(user, car, tripstart, tripend)) {
                mv.addObject("createOrderMsg", "<p>Order successfully made on specified date</p>");
            } else if (!userok) {
                mv.addObject("createOrderMsg", "<p>Please fill valid username</p>");
            } else {
                mv.addObject("createOrderMsg", "<p>Unable to order for that date</p>");
            }
        } else {
            mv.addObject("createOrderMsg", "<p>Unable to order for that date</p>");
        }
        setCommonCarProfileVariables(mv, session, car);
        return mv;
    }

    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    @RequestMapping(value = "/enableCar", method = RequestMethod.GET)
    public ModelAndView enableCar(HttpSession session, @RequestParam String id) {
        ModelAndView mv = new ModelAndView("/carProfile.jsp");
        Car car = carService.getCarById(id);
        if (carService.enable(car)) {
            mv.addObject("carChangeMsg", "Auto bylo odblokovano");
        } else {
            mv.addObject("carChangeMsg", "Auto bylo odblokovano");
        }
        setCommonCarProfileVariables(mv, session, car);
        return mv;
    }

    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    @RequestMapping(value = "/disableCar", method = RequestMethod.GET)
    public ModelAndView disableCar(HttpSession session, @RequestParam String id) {
        ModelAndView mv = new ModelAndView("/carProfile.jsp");
        Car car = carService.getCarById(id);
        if (carService.disable(car)) {
            mv.addObject("carChangeMsg", "Auto bylo zablokovano");
        } else {
            mv.addObject("carChangeMsg", "Auto nebylo zablokovano");
        }
        setCommonCarProfileVariables(mv, session, car);
        return mv;
    }

    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    @RequestMapping(value = "/addCarToFrontPage", method = RequestMethod.GET)
    public ModelAndView addCarToFrontPage(HttpSession session, @RequestParam String id) {
        ModelAndView mv = new ModelAndView("/carProfile.jsp");
        Car car = carService.getCarById(id);
        if (carService.setIsOnFrontPage(car, true)) {
            mv.addObject("carChangeMsg", "Auto bylo pridano na titulni stranku");
        } else {
            mv.addObject("carChangeMsg", "Auto nebylo pridano na titulni stranku");
        }
        setCommonCarProfileVariables(mv, session, car);
        return mv;
    }

    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    @RequestMapping(value = "/removeCarFromFrontPage", method = RequestMethod.GET)
    public ModelAndView removeCarFromFrontPage(HttpSession session, @RequestParam String id) {
        ModelAndView mv = new ModelAndView("/carProfile.jsp");
        Car car = carService.getCarById(id);
        if (carService.setIsOnFrontPage(car, false)) {
            mv.addObject("carChangeMsg", "Auto bylo odebrano z titulni stranky");
        } else {
            mv.addObject("carChangeMsg", "Auto nebylo odebrano z titulni stranky");
        }
        setCommonCarProfileVariables(mv, session, car);
        return mv;
    }

    @RequestMapping(value = "/carOrders", method = RequestMethod.GET)
    @ResponseBody
    public String fetchOrders(@RequestParam String carID) {
        Car car = carService.getCarById(carID);
        List<Carorder> carOrders = carorderService.getAllOrders(car);
        JSONArray orderDates = new JSONArray();
        for (int i = 0; i < carOrders.size(); i++) {
            JSONObject object = new JSONObject();
            JSONArray dates = new JSONArray();
            dates.put(carOrders.get(i).getBegindate());
            dates.put(carOrders.get(i).getEnddate());
            object.append("dates", dates);
            orderDates.put(dates);
        }
        return orderDates.toString();
    }

    private void setCommonCarProfileVariables(ModelAndView mv, HttpSession session, Car car) {
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        mv.addObject("minDate", formatter.format(date));
        if (session.getAttribute("UserStatus") != null && session.getAttribute("UserStatus") != AuthorityType.ROLE_CUSTOMER) {
            mv.addObject("orders", carorderService.getAllOrders(car));
            mv.addObject("disabled", "");
            if (car.isEnabled()) {
                mv.addObject("disableEnableCar", "hidden");
                mv.addObject("disableDisableCar", "");
            } else {
                mv.addObject("disableEnableCar", "");
                mv.addObject("disableDisableCar", "hidden");
            }
            if (car.isOnFrontPage()) {
                mv.addObject("disableRemoveFromFrontPageCar", "");
                mv.addObject("disableAddToFrontPageCar", "hidden");
            } else {
                mv.addObject("disableRemoveFromFrontPageCar", "hidden");
                mv.addObject("disableAddToFrontPageCar", "");
            }
        } else {
            mv.addObject("disabled", "hidden");
        }
    }
}
