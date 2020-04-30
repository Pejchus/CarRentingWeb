package tygri.pujcovna.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tygri.pujcovna.model.User;
import tygri.pujcovna.services.CarService;
import tygri.pujcovna.services.CarorderService;
import tygri.pujcovna.services.UserService;

@Controller
public class CarorderController {

    private final CarorderService orderService;
    private final UserService userService;

    @Autowired
    public CarorderController(CarService carService, CarorderService carorderService, UserService userService) {
        this.orderService = carorderService;
        this.userService = userService;
    }

    @RequestMapping(value = "/offers", method = RequestMethod.GET)
    public ModelAndView offers(HttpSession session) {
        if (session.getAttribute("userName") == null) {
            return showOffers(session, "false", "", "", "", "", orderService.getLowestEnabledCarPrice().toString(), orderService.getHighestEnabledCarPrice().toString(), "", "0");
        } else {
            return showOffers(session, "true", "", "", "", "", orderService.getLowestEnabledCarPrice().toString(), orderService.getHighestEnabledCarPrice().toString(), "", "0");
        }
    }

    @RequestMapping(value = "/filterOffers", method = RequestMethod.GET)
    public ModelAndView showOffers(HttpSession session, @RequestParam String prefferedCars, @RequestParam String modelsearch, @RequestParam String carcompany, @RequestParam String tripstart, @RequestParam String tripend, @RequestParam String range1a, @RequestParam String range1b, @RequestParam("type") String type, @RequestParam("pagestart") String pagestart) {
        ModelAndView mv = new ModelAndView("/nabidka.jsp");
        String offers;
        if ("false".equals(prefferedCars)) {
            offers = orderService.getEnabledCarsOffers(modelsearch, carcompany, tripstart, tripend, range1a, range1b, type, pagestart);
            if ("0".equals(pagestart)) {
                mv.addObject("pagingPrevious", "hidden");
            } else {
                mv.addObject("pagingPrevious", "");
            }
            if ("".equals(orderService.getEnabledCarsOffers(modelsearch, carcompany, tripstart, tripend, range1a, range1b, type, String.valueOf(Integer.valueOf(pagestart) + 10)))) {
                mv.addObject("pagingNext", "hidden");
            } else {
                mv.addObject("pagingNext", "");
            }
        } else {//only get first ten preffered cars always - predelam mozna pozdeji
            User user = userService.loadUserByUsername(session.getAttribute("userName").toString());
            offers = orderService.getEnabledPrefferd(user, pagestart);
            mv.addObject("pagingNext", "hidden");
            mv.addObject("pagingPrevious", "hidden");
        }
        mv.addObject("offers", offers);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        mv.addObject("minDate", formatter.format(date));
        mv.addObject("minSliderVal", orderService.getLowestEnabledCarPrice());
        mv.addObject("maxSliderVal", orderService.getHighestEnabledCarPrice());
        mv.addObject("modelsearchValue", modelsearch);
        resolveCheckedcarcompany(mv, carcompany);
        mv.addObject("tripstartValue", tripstart);
        mv.addObject("tripendValue", tripend);
        mv.addObject("range1aValue", range1a);
        mv.addObject("range1bValue", range1b);
        mv.addObject("range1bValue", range1b);
        mv.addObject("pagestart", pagestart);
        mv.addObject("previouspagestart", String.valueOf(Integer.valueOf(pagestart) - 9));
        mv.addObject("nextpagestart", String.valueOf(Integer.valueOf(pagestart) + 9));
        resolveCheckedType(mv, type);
        return mv;
    }

    private void resolveCheckedType(ModelAndView mv, String type) {
        mv.addObject("typeValue", type);
        switch (type) {
            case "all":
            case "":
                mv.addObject("allchecked", "checked");
                break;
            case "CABRIOLET":
                mv.addObject("CABRIOLETchecked", "checked");
                break;
            case "COMBI":
                mv.addObject("COMBIchecked", "checked");
                break;
            case "VAN":
                mv.addObject("VANchecked", "checked");
                break;
            case "HATCHBACK":
                mv.addObject("HATCHBACKchecked", "checked");
                break;
            case "PICKUP":
                mv.addObject("PICKUPchecked", "checked");
                break;
            case "SEDAN":
                mv.addObject("SEDANchecked", "checked");
                break;
            case "SUV":
                mv.addObject("SUVchecked", "checked");
                break;
        }
    }

    private void resolveCheckedcarcompany(ModelAndView mv, String carcompany) {
        mv.addObject("carcompanyValue", carcompany);
        switch (carcompany) {
            case "vsechny":
            case "":
                mv.addObject("vsechnychecked", "selected");
                break;
            case "Volvo":
                mv.addObject("Volvochecked", "selected");
                break;
            case "Saab":
                mv.addObject("Saabchecked", "selected");
                break;
            case "Opel":
                mv.addObject("Opelchecked", "selected");
                break;
            case "Audi":
                mv.addObject("Audichecked", "selected");
                break;
            case "BMW":
                mv.addObject("BMWchecked", "selected");
                break;
            case "Skoda":
                mv.addObject("Skodachecked", "selected");
                break;
            case "Jaguar":
                mv.addObject("Jaguarchecked", "selected");
                break;
            case "Citroen":
                mv.addObject("Citroenchecked", "selected");
                break;
            case "Vw":
                mv.addObject("Vwchecked", "selected");
                break;
            case "Hyundai":
                mv.addObject("Hyundaichecked", "selected");
                break;
        }
    }
}
