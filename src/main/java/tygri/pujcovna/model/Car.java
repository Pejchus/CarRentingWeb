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
    private Double baseprice;

    @Basic(optional = false)
    @Column(nullable = false)
    private String color;

    @Basic(optional = false)
    @Column(nullable = false)
    private Double power;

    @Basic(optional = false)
    @Column(nullable = false)
    private Integer productionyear;

    @Basic(optional = false)
    @Column(nullable = false)
    private Double trunkvolume;

    @Basic(optional = false)
    @Column(nullable = false)
    private boolean foldingrearseats;

    @Basic(optional = false)
    @Column(nullable = false)
    private Integer seats;

    @Basic(optional = false)
    @Column(nullable = false)
    private Double consumption;

    @Basic(optional = false)
    @Column(nullable = false)
    private String description;

    @Lob
    private Byte[] photo;

    @Basic(optional = false)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CarCategory carCategory;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Carorder> orders;

    public Car() {
    }

    public Car(String model, String brand, Double baseprice, String color, Double power, Integer productionyear, Double trunkvolume, boolean foldingrearseats, Integer seats, Double consumption, String description, Byte[] photo, CarCategory carCategory) {
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
        this.photo = photo;
        this.carCategory = carCategory;
    }

    public Car(String model, String brand, Double baseprice, String color, Double power, Integer productionyear, Double trunkvolume, boolean foldingrearseats, Integer seats, Double consumption, String description, CarCategory carCategory) {
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
        this.carCategory = carCategory;
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

    public Double getBaseprice() {
        return baseprice;
    }

    public void setBaseprice(Double baseprice) {
        this.baseprice = baseprice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Integer getProductionyear() {
        return productionyear;
    }

    public void setProductionyear(Integer productionyear) {
        this.productionyear = productionyear;
    }

    public double getTrunkvolume() {
        return trunkvolume;
    }

    public void setTrunkvolume(Double trunkvolume) {
        this.trunkvolume = trunkvolume;
    }

    public boolean isFoldingrearseats() {
        return foldingrearseats;
    }

    public void setFoldingrearseats(boolean foldingrearseats) {
        this.foldingrearseats = foldingrearseats;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
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

    public Byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }

    public CarCategory getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(CarCategory carCategory) {
        this.carCategory = carCategory;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", model=" + model + ", brand=" + brand + ", baseprice=" + baseprice + ", color=" + color + ", power=" + power + ", productionyear=" + productionyear + ", trunkvolume=" + trunkvolume + ", foldingrearseats=" + foldingrearseats + ", seats=" + seats + ", consumption=" + consumption + ", description=" + description + ", carCategory=" + carCategory + '}';
    }
}
