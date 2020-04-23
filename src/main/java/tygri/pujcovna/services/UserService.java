package tygri.pujcovna.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tygri.pujcovna.dao.UserAndAuthorityDao;
import tygri.pujcovna.model.AuthorityType;
import tygri.pujcovna.model.User;

@Service
public class UserService implements UserDetailsService {

    private final UserAndAuthorityDao userAndAuthorityDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserAndAuthorityDao userRepository) {
        this.userAndAuthorityDao = userRepository;
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
    public List<User> getAllUsers() {
        return userAndAuthorityDao.getAll();
    }

    @Transactional
    public boolean isUniqueUsername(String username) {
        User user = userAndAuthorityDao.getUserByUsername(username);
        return user == null;
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
            return "</p><img src=\"data:image/png;base64," + " " + "\" alt=\"Profilove foto\" height=\"100\" width=\"100\"/>";
        }
        byte[] photobytes = new byte[photo.length];
        int i = 0;
        for (Byte b : photo) {
            photobytes[i++] = b;
        }
        String photoData = Base64.getEncoder().encodeToString(photobytes);
        return "</p><img src=\"data:image/png;base64," + photoData + "\" alt=\"Profilove foto\" height=\"100\" width=\"100\"/>";
    }
}
