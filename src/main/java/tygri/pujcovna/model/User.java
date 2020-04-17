package tygri.pujcovna.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User implements Serializable {

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carorder> orders;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

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

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", enabled=" + enabled + ", username=" + username + ", password=" + password + ", email=" + email + ", phone=" + phone + ", countryCode=" + countryCode + ", firstname=" + firstname + ", lastname=" + lastname + ", city=" + city + ", street=" + street + ", streetno=" + streetno + ", accroles=" + authorities + ", orders=" + orders + '}';
    }
}
