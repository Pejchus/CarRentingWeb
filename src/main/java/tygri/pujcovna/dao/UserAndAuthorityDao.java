package tygri.pujcovna.dao;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.TypedQuery;

import tygri.pujcovna.model.Car;
import tygri.pujcovna.model.User;
import org.springframework.stereotype.Repository;
import tygri.pujcovna.model.Authority;
import tygri.pujcovna.model.AuthorityType;

@Repository
public class UserAndAuthorityDao extends BaseDao /*implements UserRepository*/ {

    public UserAndAuthorityDao() {
        super(User.class);
    }

    public User getUserByUsername(String username) {
        try {
            TypedQuery<User> query = em.createQuery("FROM User u where u.username=:username", User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean create(String username, String password, String email, boolean booleanEnabled, String phone, String countryCode, String firstname, String lastname, String city, String street, String streetNo, AuthorityType AUTHORITYTYPE) {
        try {
            TypedQuery<Authority> query = em.createQuery("FROM Authority a where a.name=:name", Authority.class);
            query.setParameter("name", AUTHORITYTYPE);
            User user = new User(booleanEnabled, username, password, email, phone, countryCode, firstname, lastname, city, street, streetNo);
            Set<Authority> authorities = Collections.singleton(query.getSingleResult());
            user.setAuthorities(authorities);
            persist(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /* public List<User> findAll() {
        return super.getAll();
    }*/
    public boolean createAuthority(AuthorityType name) {
        Authority auth = new Authority(name);
        try {
            em.persist(auth);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean deleteUserByUsername(String username) {
        try {
            User toDelete = em.find(User.class, username);
            super.remove(toDelete);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<User> getBannedUsers() {
        try {
            return em.createQuery("SELECT e FROM User e WHERE e.enabled =false", User.class).getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException("No banned user");
        }
    }

    public boolean switchUserEnability(String username) {
        User toBan = getUserByUsername(username);
        try {
            Objects.requireNonNull(toBan, "trying to ban null object");
            toBan.setEnabled(!toBan.isEnabled());
            super.updateEntity(toBan);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}