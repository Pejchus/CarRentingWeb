package tygri.pujcovna.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Role accrole;

    @Basic(optional = false)
    @Column(nullable = false)
    private boolean enabled;

    @Basic(optional = false)
    @Column(nullable = false)
    private String username;

    @Basic(optional = false)
    @Column(nullable = false)
    private String password;

    @Basic(optional = false)
    @Column(nullable = false)
    private String email;

    @Basic(optional = false)
    @Column(nullable = false)
    private int phone;

    @Basic(optional = false)
    @Column(nullable = false)
    private int countryCode;

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

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Carorder> orders;

    public Account() {
    }

    public Account(boolean enabled, String username, String password, String email, int phone, int countryCode, String firstname, String lastname, String city, String street, String streetno, Role accrole) {
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
        this.accrole = accrole;
    }

    public List<Carorder> getOrders() {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        return orders;
    }

    public void addOrder(Carorder order) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
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

    public Role getAccrole() {
        return accrole;
    }

    public void setAccrole(Role accrole) {
        this.accrole = accrole;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", enabled=" + enabled + ", username=" + username + ", password=" + password + ", email=" + email + ", phone=" + phone + ", countryCode=" + countryCode + ", firstname=" + firstname + ", lastname=" + lastname + ", city=" + city + ", street=" + street + ", streetno=" + streetno + ", accrole=" + accrole + ", orders=" + orders + '}';
    }
}
