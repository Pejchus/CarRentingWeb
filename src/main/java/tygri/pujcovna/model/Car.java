package tygri.pujcovna.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "CAR")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Basic(optional = false)
    @Column(nullable = false)
    private String model;

    @Basic(optional = false)
    @Column(nullable = false)
    private String brand;

    @Basic(optional = false)
    @Column(nullable = false)
    private double baseprice;

    @Basic(optional = false)
    @Column(nullable = false)
    private String color;

    @Basic(optional = false)
    @Column(nullable = false)
    private double power;

    @Basic(optional = false)
    @Column(nullable = false)
    private int productionyear;

    @Basic(optional = false)
    @Column(nullable = false)
    private double trunkvolume;

    @Basic(optional = false)
    @Column(nullable = false)
    private boolean foldingrearseats;

    @Basic(optional = false)
    @Column(nullable = false)
    private int seats;

    @Basic(optional = false)
    @Column(nullable = false)
    private double consumption;

    @Basic(optional = false)
    @Column(nullable = false)
    private String description;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Carorder> orders;

    public Car() {
    }

    public Car(String model, String brand, double baseprice, String color, double power, int productionyear, double trunkvolume, boolean foldingrearseats, int seats, double consumption, String description) {
        this.model = model;
        this.brand = brand;
        this.baseprice = baseprice;
        this.color = color;
        this.power = power;
        this.productionyear = productionyear;
        this.trunkvolume = trunkvolume;
        this.foldingrearseats = foldingrearseats;
        this.seats = seats;
        this.consumption = consumption;
        this.description = description;
    }

    public List<Carorder> getOrders() {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        return orders;
    }

    public Integer getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getBaseprice() {
        return baseprice;
    }

    public void setBaseprice(double baseprice) {
        this.baseprice = baseprice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public int getProductionyear() {
        return productionyear;
    }

    public void setProductionyear(int productionyear) {
        this.productionyear = productionyear;
    }

    public double getTrunkvolume() {
        return trunkvolume;
    }

    public void setTrunkvolume(double trunkvolume) {
        this.trunkvolume = trunkvolume;
    }

    public boolean isFoldingrearseats() {
        return foldingrearseats;
    }

    public void setFoldingrearseats(boolean foldingrearseats) {
        this.foldingrearseats = foldingrearseats;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", model=" + model + ", brand=" + brand + ", baseprice=" + baseprice + ", color=" + color + ", power=" + power + ", productionyear=" + productionyear + ", trunkvolume=" + trunkvolume + ", foldingrearseats=" + foldingrearseats + ", seats=" + seats + ", consumption=" + consumption + '}';
    }

}
