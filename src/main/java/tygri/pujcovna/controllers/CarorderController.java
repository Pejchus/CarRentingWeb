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
import tygri.pujcovna.services.CarorderService;

@Controller
public class CarorderController {

    @Autowired
    private CarorderService orderService;

    @RequestMapping(value = "/offers", method = RequestMethod.GET)
    public ModelAndView offers(HttpSession session) {
        return showOffers(session, "", "", "", "", "", "", "");
    }

    @RequestMapping(value = "/filterOffers", method = RequestMethod.GET)
    public ModelAndView showOffers(HttpSession session, @RequestParam String modelsearch, @RequestParam String carcompany, @RequestParam String tripstart, @RequestParam String tripend, @RequestParam String range1a, @RequestParam String range1b, @RequestParam("type") String type) {
        ModelAndView mv = new ModelAndView("/nabidka.jsp");
        String offers = orderService.getOffers(modelsearch, carcompany, tripstart, tripend, range1a, range1b, type);
        mv.addObject("offers", offers);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        mv.addObject("minDate", formatter.format(date));
        return mv;
    }
}
