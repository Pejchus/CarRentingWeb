package tygri.pujcovna.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tygri.pujcovna.dao.CarorderDao;
import tygri.pujcovna.dao.UserAndAuthorityDao;
import tygri.pujcovna.model.AuthorityType;
import tygri.pujcovna.model.Carorder;
import tygri.pujcovna.model.User;
import tygri.pujcovna.other.Constants;

@Service
public class UserService implements UserDetailsService {

    private final CarorderDao carorderDao;
    private final UserAndAuthorityDao userAndAuthorityDao;
    private final Constants constants;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserAndAuthorityDao userRepository, Constants constants, CarorderDao carorderDao) {
        this.userAndAuthorityDao = userRepository;
        this.constants = constants;
        this.carorderDao = carorderDao;
    }

    @Transactional
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userAndAuthorityDao.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        System.out.println("loadUserByUsername() : " + username);
        return user;
    }

    @Transactional
    public String getAllUsersPreviews(String pagestart) {
        return getUsersPreviews(userAndAuthorityDao.getAllUsersPaged(Integer.valueOf(pagestart)));
    }

    @Transactional
    public String getUsersPreviews(List<User> users) {
        StringBuilder sb = new StringBuilder(10000);
        for (User user : users) {
            String previewString = constants.getUserPreview();
            Byte[] carPhoto = user.getPhoto();
            String photoData = "";
            if (carPhoto != null) {
                byte[] photobytes = new byte[carPhoto.length];
                int i = 0;
                for (Byte b : carPhoto) {
                    photobytes[i++] = b;
                }
                photoData = Base64.getEncoder().encodeToString(photobytes);
            }
            previewString = previewString.replaceAll(";userProfileLink;", "adminProfileView?id=" + user.getId());
            previewString = previewString.replaceFirst(";userName;", user.getUsername());
            previewString = previewString.replaceFirst(";userType;", user.getAuthorities().iterator().next().toString());
            previewString = previewString.replaceFirst(";userPhotoData;", photoData);
            sb.append(previewString);
        }
        return sb.toString();
    }

    /* @Transactional
    public boolean isUniqueUsername(String username) {
        User user = userAndAuthorityDao.getUserByUsername(username);
        return user == null;
    }*/
    @Transactional

    public String isOK(String username, String email, String phone, String countryCode, String firstname, String lastname, String city, String street, String streetNo) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        if (!mat.matches()) {
            return "Invalid email";
        }
        pattern = Pattern.compile("[0-9]{9}");
        mat = pattern.matcher(phone);
        if (!mat.matches()) {
            return "Invalid phone";
        }
        return null;
    }

    @Transactional
    public String isUnique(String username, String email, String phone) {
        if (userAndAuthorityDao.getUserByUsername(username) != null) {
            return "<p>Username obsazeno</p>";
        }
        if (!userAndAuthorityDao.uniqueEmail(email)) {
            return "<p>Email již registrován</p>";
        }
        if (!userAndAuthorityDao.uniquePhone(phone)) {
            return "<p>Telefon již registrován</p>";
        }
        return null;
    }

    @Transactional
    public boolean createUser(String username, String password, String email, String enabled, String phone, String countryCode, String firstname, String lastname, String city, String street, String streetNo, String authority) {
        try {
            boolean booleanEnabled;
            if ("true".equals(enabled)) {
                booleanEnabled = true;
            } else if ("false".equals(enabled)) {
                booleanEnabled = false;
            } else {
                throw new NumberFormatException("Enabled must be true/false");
            }
            AuthorityType AUTHORITYTYPE;
            if ("CUSTOMER".equals(authority)) {
                AUTHORITYTYPE = AuthorityType.ROLE_CUSTOMER;
            } else if ("EMPLOYEE".equals(authority)) {
                AUTHORITYTYPE = AuthorityType.ROLE_EMPLOYEE;
            } else if ("ADMIN".equals(authority)) {
                AUTHORITYTYPE = AuthorityType.ROLE_ADMIN;
            } else {
                throw new NumberFormatException("Authority must be CUSTOMER/EMPLOYEE/ADMIN");
            }
            String encodedPassword = passwordEncoder.encode(password);
            return userAndAuthorityDao.create(username, encodedPassword, email, booleanEnabled, phone, countryCode, firstname, lastname, city, street, streetNo, AUTHORITYTYPE);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean createAuthority(AuthorityType type) {
        return userAndAuthorityDao.createAuthority(type);
    }

    public boolean setPhoto(MultipartFile photo, String username) {
        try {
            Byte[] photoCopy = new Byte[photo.getBytes().length];
            int i = 0;
            for (Byte b : photo.getBytes()) {
                photoCopy[i++] = b;
            }
            return userAndAuthorityDao.setPhoto(photoCopy, username);
        } catch (IOException e) {
            System.out.println("photo upload fail");
            return false;
        }
    }

    public String getPhoto(String userName) {
        Byte[] photo = loadUserByUsername(userName).getPhoto();
        if (photo == null) {
            return "<img src=\"data:image/png;base64," + " " + "\" alt=\"Profilove foto\" height=\"100\" width=\"100\"/>";
        }
        byte[] photobytes = new byte[photo.length];
        int i = 0;
        for (Byte b : photo) {
            photobytes[i++] = b;
        }
        String photoData = Base64.getEncoder().encodeToString(photobytes);
        return "<img src=\"data:image/png;base64," + photoData + "\" alt=\"Profilove foto\" height=\"100\" width=\"100\"/>";
    }

    @Transactional
    public User loadUserById(String id) {
        try {
            return userAndAuthorityDao.getUserById(Integer.valueOf(id));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean enable(HttpSession session, User user) {
        if (session.getAttribute("userId") != user.getId()) {
            return userAndAuthorityDao.setEnabled(true, user.getId());
        } else {
            return false;
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public boolean disable(HttpSession session, User user) {
        if (session.getAttribute("userId") != user.getId()) {
            return userAndAuthorityDao.setEnabled(false, user.getId());
        } else {
            return false;
        }
    }

    public boolean deleteUser(HttpSession session, User user) {
        if (session.getAttribute("userId") != user.getId()) {
            for (Carorder order : user.getOrders()) {
                carorderDao.setOrdersUser(order, null);
            }
            return userAndAuthorityDao.removeUser(user);
        } else {
            return false;
        }
    }
}
