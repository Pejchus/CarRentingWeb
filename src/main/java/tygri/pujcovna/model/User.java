package tygri.pujcovna.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "USER")
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private String username;

    @Basic(optional = false)
    @Column(nullable = false)
    private String password;

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private String email;

    @Basic(optional = false)
    @Column(nullable = false)
    private boolean enabled;

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private String phone;

    @Basic(optional = false)
    @Column(nullable = false)
    private String countryCode;

    @Basic(optional = false)
    @Column(nullable = false)
    private String firstname;

    @Basic(optional = false)
    @Column(nullable = false)
    private String lastname;

    @Basic(optional = false)
    @Column(nullable = false)
    private String city;

    @Basic(optional = false)
    @Column(nullable = false)
    private String street;

    @Basic(optional = false)
    @Column(nullable = false)
    private String streetno;

    @Lob
    private Byte[] photo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carorder> orders;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Authority> userAuthorities = new HashSet<>();

    public User() {
    }

    public User(boolean enabled, String username, String password, String email, String phone, String countryCode, String firstname, String lastname, String city, String street, String streetno) {
        this.enabled = enabled;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.countryCode = countryCode;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.street = street;
        this.streetno = streetno;
        this.orders = new ArrayList<>();
    }

    public List<Carorder> getOrders() {
        return orders;
    }

    public void addOrder(Carorder order) {
        orders.add(Objects.requireNonNull(order));
    }

    public Integer getId() {
        return id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetno() {
        return streetno;
    }

    public void setStreetno(String streetno) {
        this.streetno = streetno;
    }

    public Set<Authority> getUserAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(Set<Authority> authorities) {
        this.userAuthorities = authorities;
    }

    public Byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", enabled=" + enabled + ", username=" + username + ", password=" + password + ", email=" + email + ", phone=" + phone + ", countryCode=" + countryCode + ", firstname=" + firstname + ", lastname=" + lastname + ", city=" + city + ", street=" + street + ", streetno=" + streetno + ", accroles=" + userAuthorities + ", orders=" + orders + '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getUserAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getName().toString())).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }
}
