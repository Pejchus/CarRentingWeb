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
import tygri.pujcovna.model.AuthorityType;
import tygri.pujcovna.model.Car;
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
        setCommonProfileVariables(session, mv, session.getAttribute("userId").toString());
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
        setCommonProfileVariables(session, mv, session.getAttribute("userId").toString());
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
                setCommonProfileVariables(session, mv, id);
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
                setCommonProfileVariables(session, mv, id);
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
                setCommonProfileVariables(session, mv, id);
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

    private void setCommonProfileVariables(HttpSession session, ModelAndView mv, String id) {
        User user = userService.loadUserById(id);
        mv.addObject("firstname", user.getFirstname());
        mv.addObject("lastname", user.getLastname());
        mv.addObject("phone", user.getPhone());
        mv.addObject("email", user.getEmail());
        mv.addObject("countrycode", user.getCountryCode());
        mv.addObject("city", user.getCity());
        mv.addObject("street", user.getStreet());
        mv.addObject("streetno", user.getStreetno());
        mv.addObject("profilePhoto", userService.getPhoto(user.getUsername()));
        mv.addObject("orders", carorderService.getAllOrders(userService.loadUserByUsername(user.getUsername())));
    }

    @RequestMapping(value = "/adminPage")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    public ModelAndView getAdminPage(HttpSession session) {
        return getAdminPagePaged(session, "0", "0");
    }

    //BUGGED
    @RequestMapping(value = "/adminPagepaged")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    public ModelAndView getAdminPagePaged(HttpSession session, @RequestParam String carpagestart, @RequestParam String userpagestart) {
        ModelAndView mv = new ModelAndView("/adminPage.jsp");
        mv.addObject("carData", carService.getAllCarsPreviews(carpagestart));
        if (session.getAttribute("UserStatus") == AuthorityType.ROLE_ADMIN) {
            mv.addObject("userData", userService.getAllUsersPreviews(userpagestart));
            if (!"".equals(userService.getAllUsersPreviews(String.valueOf(Integer.valueOf(carpagestart) + 10)))) {
                mv.addObject("paginguserNext", "");
            } else {
                mv.addObject("paginguserNext", "hidden");
            }
            if (!"0".equals(userpagestart)) {
                mv.addObject("paginguserPrevious", "");
            } else {
                mv.addObject("paginguserPrevious", "hidden");
            }
            mv.addObject("previoususerpagestart", String.valueOf(Integer.valueOf(userpagestart) - 10));
            mv.addObject("nextuserpagestart", String.valueOf(Integer.valueOf(userpagestart) + 10));
            mv.addObject("currentuserpagestart", String.valueOf(Integer.valueOf(userpagestart)));
        }
        if (!"".equals(carService.getAllCarsPreviews(String.valueOf(Integer.valueOf(carpagestart) + 10)))) {
            mv.addObject("pagingcarNext", "");
        } else {
            mv.addObject("pagingcarNext", "hidden");
        }
        if (!"0".equals(carpagestart)) {
            mv.addObject("pagingcarPrevious", "");
        } else {
            mv.addObject("pagingcarPrevious", "hidden");
        }
        mv.addObject("nextcarpagestart", String.valueOf(Integer.valueOf(carpagestart) + 10));
        mv.addObject("currentcarpagestart", String.valueOf(Integer.valueOf(carpagestart)));
        mv.addObject("previouscarpagestart", String.valueOf(Integer.valueOf(carpagestart) - 10));
        return mv;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public ModelAndView deleteUser(HttpSession session, @RequestParam String id) {
        ModelAndView mv = getAdminPagePaged(session, "0", "0");
        User user = userService.loadUserById(id);
        if (userService.deleteUser(session, user)) {
            mv.addObject("changeMessage", "Uzivatel byl smazan");
        } else {
            mv.addObject("changeMessage", "Uzivatel nebyl smazan");
        }
        return mv;
    }

    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    @RequestMapping(value = "/deleteCar", method = RequestMethod.GET)
    public ModelAndView deleteCar(HttpSession session, @RequestParam String id) {
        ModelAndView mv = getAdminPagePaged(session, "0", "0");
        Car car = carService.getCarById(id);
        if (carService.deleteCar(car)) {
            mv.addObject("changeMessage", "Auto bylo smazano");
        } else {
            mv.addObject("changeMessage", "Auto nebylo smazano");
        }
        return mv;
    }

    @RequestMapping("/doAddUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView doAddUser(HttpSession session, @RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String enabled, @RequestParam String phone, @RequestParam String countryCode, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String city, @RequestParam String street, @RequestParam String streetNo, @RequestParam String authority) {
        ModelAndView mv = getAdminPagePaged(session, "0", "0");
        if (userService.createUser(username, password, email, enabled, phone, countryCode, firstname, lastname, city, street, streetNo, authority)) {
            mv.addObject("changeMessage", "<p>User added!</p>");
        } else {
            mv.addObject("changeMessage", "<p>User not added!</p>");
        }
        return mv;
    }

    @RequestMapping(value = "/doAddCar", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    public ModelAndView doAddCar(HttpSession session, @RequestParam("model") String model, @RequestParam("brand") String brand, @RequestParam("baseprice") String baseprice, @RequestParam("color") String color, @RequestParam("power") String power, @RequestParam("productionyear") String productionyear, @RequestParam("trunkvolume") String trunkvolume, @RequestParam("seats") String seats, @RequestParam("consumption") String consumption, @RequestParam("transmissiontype") String transmissiontype, @RequestParam("enginetype") String enginetype, @RequestParam("description") String description, @RequestParam("photo") MultipartFile photo, @RequestParam("carcategory") String carcategory) {
        ModelAndView mv = getAdminPagePaged(session, "0", "0");
        if (carService.createCar(model, brand, baseprice, color, power, productionyear, trunkvolume, seats, consumption, transmissiontype, enginetype, description, photo, carcategory)) {
            mv.addObject("carAddedMessage", "<p>Car added!</p>");
        } else {
            mv.addObject("carAddedMessage", "<p>Car not added!</p>");
        }
        return mv;
    }
}
