package tygri.pujcovna.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tygri.pujcovna.dao.UserAndAuthorityDao;
import tygri.pujcovna.model.AuthorityType;
import tygri.pujcovna.model.User;
import tygri.pujcovna.model.UserAuthoritiesWrapper;

@Service
public class UserService implements UserDetailsService {

    private final UserAndAuthorityDao userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserAndAuthorityDao userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        System.out.println("loadUserByUsername() : " + username);
        return new UserAuthoritiesWrapper(user);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.getAll();
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
            return userRepository.create(username, encodedPassword, email, booleanEnabled, phone, countryCode, firstname, lastname, city, street, streetNo, AUTHORITYTYPE);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean createAuthority(AuthorityType type) {
        return userRepository.createAuthority(type);
    }
}
