package tygri.pujcovna.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import tygri.pujcovna.model.User;
import tygri.pujcovna.services.CarService;
import tygri.pujcovna.services.CarorderService;
import tygri.pujcovna.services.UserService;

@Controller
public class ProfileController {

    private final UserService userService;
    private final CarService carService;
    private final CarorderService carorderService;

    @Autowired
    public ProfileController(UserService userService, CarService carService, CarorderService carorderService) {
        this.userService = userService;
        this.carService = carService;
        this.carorderService = carorderService;
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')")
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(HttpSession session) {
        ModelAndView mv = new ModelAndView("/profile.jsp");
        setCommonProfileVariables(session, mv);
        mv.addObject("disabled", "");
        mv.addObject("disabledAdminButtons", "hidden");
        mv.addObject("disableEnableUser", "hidden");
        mv.addObject("disableDisableUser", "hidden");
        return mv;
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_EMPLOYEE','ROLE_ADMIN')")
    @RequestMapping(value = "/setProfilePhoto", method = RequestMethod.POST)
    public ModelAndView setProfilePhoto(HttpSession session, @RequestParam MultipartFile photo) {
        ModelAndView mv = new ModelAndView("/profile.jsp");
        if (userService.setPhoto(photo, session.getAttribute("userName").toString())) {
            mv.addObject("profilePhotoChangeMsg", "<p>Profilove foto uspesne zmeneno</p>");
        } else {
            mv.addObject("profilePhotoChangeMsg", "<p>Profilove foto nebylo zmeneno</p>");
        }
        setCommonProfileVariables(session, mv);
        mv.addObject("disabled", "");
        mv.addObject("disabledAdminButtons", "hidden");
        mv.addObject("disableEnableUser", "hidden");
        mv.addObject("disableDisableUser", "hidden");
        return mv;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/adminProfileView", method = RequestMethod.GET)
    public ModelAndView adminProfileView(HttpSession session, @RequestParam String id) {
        if (session.getAttribute("userId").toString().equals(id)) {
            return profile(session);
        } else {
            ModelAndView mv = new ModelAndView("/profile.jsp");
            User user = userService.loadUserById(id);
            if (user != null) {
                setCommonProfileVariables(session, mv);
                if (user.isEnabled()) {
                    mv.addObject("disableEnableUser", "hidden");
                    mv.addObject("disableDisableUser", "");
                } else {
                    mv.addObject("disableEnableUser", "");
                    mv.addObject("disableDisableUser", "hidden");
                }
            } else {
                return new ModelAndView("/error.html");
            }
            mv.addObject("userId", id);
            mv.addObject("disabled", "hidden");
            mv.addObject("disabledAdminButtons", "");
            return mv;
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/disableUser", method = RequestMethod.GET)
    public ModelAndView disableUser(HttpSession session, @RequestParam String id) {
        if (session.getAttribute("userId").toString().equals(id)) {
            return profile(session);
        } else {
            ModelAndView mv = new ModelAndView("/profile.jsp");
            User user = userService.loadUserById(id);
            if (userService.disable(session, user)) {
                mv.addObject("changeMessage", "Uzivatel byl zablokovan");
            } else {
                mv.addObject("changeMessage", "Uzivatel nebyl zablokovan");
            }
            if (user != null) {
                setCommonProfileVariables(session, mv);
                if (user.isEnabled()) {
                    mv.addObject("disableEnableUser", "hidden");
                    mv.addObject("disableDisableUser", "");
                } else {
                    mv.addObject("disableEnableUser", "");
                    mv.addObject("disableDisableUser", "hidden");
                }
            } else {
                return new ModelAndView("/error.html");
            }
            mv.addObject("disabledAdminButtons", "");
            mv.addObject("userId", id);
            mv.addObject("disabled", "hidden");
            return mv;
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/enableUser", method = RequestMethod.GET)
    public ModelAndView enableUser(HttpSession session, @RequestParam String id) {
        if (session.getAttribute("userId").toString().equals(id)) {
            return profile(session);
        } else {
            ModelAndView mv = new ModelAndView("/profile.jsp");
            User user = userService.loadUserById(id);
            if (userService.enable(session, user)) {
                mv.addObject("changeMessage", "Uzivatel byl odblokovan");
            } else {
                mv.addObject("changeMessage", "Uzivatel byl odblokovan");
            }
            if (user != null) {
                setCommonProfileVariables(session, mv);
                if (user.isEnabled()) {
                    mv.addObject("disableEnableUser", "hidden");
                    mv.addObject("disableDisableUser", "");
                } else {
                    mv.addObject("disableEnableUser", "");
                    mv.addObject("disableDisableUser", "hidden");
                }
            } else {
                return new ModelAndView("/error.html");
            }
            mv.addObject("disabledAdminButtons", "");
            mv.addObject("userId", id);
            mv.addObject("disabled", "hidden");
            return mv;
        }
    }

    private void setCommonProfileVariables(HttpSession session, ModelAndView mv) {
        mv.addObject("firstname", session.getAttribute("firstname"));
        mv.addObject("lastname", session.getAttribute("lastname"));
        mv.addObject("phone", session.getAttribute("phone"));
        mv.addObject("email", session.getAttribute("email"));
        mv.addObject("countrycode", session.getAttribute("countrycode"));
        mv.addObject("city", session.getAttribute("city"));
        mv.addObject("street", session.getAttribute("street"));
        mv.addObject("streetno", session.getAttribute("streetno"));
        mv.addObject("profilePhoto", userService.getPhoto(session.getAttribute("userName").toString()));
        mv.addObject("orders", carorderService.getAllOrders(userService.loadUserByUsername(session.getAttribute("userName").toString())));
    }

    @RequestMapping(value = "/adminPage")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    public ModelAndView getAdminPage(HttpSession session) {
        ModelAndView mv = new ModelAndView("/adminPage.jsp");
        mv.addObject("carData", carService.getAllCarsPreviews());
        mv.addObject("userData", userService.getAllUsersPreviews());
        return mv;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public ModelAndView deleteUser(HttpSession session, @RequestParam String id) {
        ModelAndView mv = new ModelAndView("adminPage.jsp");
        User user = userService.loadUserById(id);
        if (userService.deleteUser(session, user)) {
            mv.addObject("changeMessage", "Uzivatel byl smazan");
        } else {
            mv.addObject("changeMessage", "Uzivatel nebyl smazan");
        }
        mv.addObject("carData", carService.getAllCarsPreviews());
        mv.addObject("userData", userService.getAllUsersPreviews());
        return mv;
    }

    @RequestMapping(value = "/doAddCar", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    public ModelAndView doAddCar(HttpSession session, @RequestParam("model") String model, @RequestParam("brand") String brand, @RequestParam("baseprice") String baseprice, @RequestParam("color") String color, @RequestParam("power") String power, @RequestParam("productionyear") String productionyear, @RequestParam("trunkvolume") String trunkvolume, @RequestParam("seats") String seats, @RequestParam("consumption") String consumption, @RequestParam("transmissiontype") String transmissiontype, @RequestParam("enginetype") String enginetype, @RequestParam("description") String description, @RequestParam("photo") MultipartFile photo, @RequestParam("carcategory") String carcategory) {
        ModelAndView mv = new ModelAndView("/adminPage.jsp");
        if (carService.createCar(model, brand, baseprice, color, power, productionyear, trunkvolume, seats, consumption, transmissiontype, enginetype, description, photo, carcategory)) {
            mv.addObject("carAddedMessage", "<p>Car added!</p>");
        } else {
            mv.addObject("carAddedMessage", "<p>Car not added!</p>");
        }
        mv.addObject("carData", carService.getAllCarsPreviews());
        mv.addObject("userData", userService.getAllUsersPreviews());
        mv.addObject("LoggedUser", session.getAttribute("userName"));
        return mv;
    }

    @RequestMapping("/doAddUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView doAddUser(HttpSession session, @RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String enabled, @RequestParam String phone, @RequestParam String countryCode, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String city, @RequestParam String street, @RequestParam String streetNo, @RequestParam String authority) {
        ModelAndView mv = new ModelAndView("/adminPage.jsp");
        if (userService.createUser(username, password, email, enabled, phone, countryCode, firstname, lastname, city, street, streetNo, authority)) {
            mv.addObject("changeMessage", "<p>User added!</p>");
        } else {
            mv.addObject("changeMessage", "<p>User not added!</p>");
        }
        mv.addObject("userData", userService.getAllUsersPreviews());
        mv.addObject("carData", carService.getAllCarsPreviews());
        mv.addObject("LoggedUser", session.getAttribute("userName"));
        return mv;
    }

}
