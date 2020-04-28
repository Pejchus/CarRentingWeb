package tygri.pujcovna.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.management.Query;
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
    private boolean enabled;

    @Basic(optional = false)
    @Column(nullable = false)
    private Integer seats;

    @Basic(optional = false)
    @Column(nullable = false)
    private Double consumption;

    @Basic(optional = false)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransmissionType transimissionType;

    @Basic(optional = false)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

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
            orphanRemoval = true,
            fetch = FetchType.EAGER,
            mappedBy = "car"
    )
    private List<Carorder> orderss;

    public Car() {
    }

    public Car(String model, String brand, Double baseprice, String color, Double power, Integer productionyear, Double trunkvolume, boolean enabled, Integer seats, Double consumption, TransmissionType transimissionType, EngineType engineType, String description, CarCategory carCategory) {
        this.model = model;
        this.brand = brand;
        this.baseprice = baseprice;
        this.color = color;
        this.power = power;
        this.productionyear = productionyear;
        this.trunkvolume = trunkvolume;
        this.enabled = enabled;
        this.seats = seats;
        this.consumption = consumption;
        this.transimissionType = transimissionType;
        this.engineType = engineType;
        this.description = description;
        this.carCategory = carCategory;
    }

    public Car(String model, String brand, Double baseprice, String color, Double power, Integer productionyear, Double trunkvolume, boolean enabled, Integer seats, Double consumption, TransmissionType transimissionType, EngineType engineType, String description, Byte[] photo, CarCategory carCategory) {
        this.model = model;
        this.brand = brand;
        this.baseprice = baseprice;
        this.color = color;
        this.power = power;
        this.productionyear = productionyear;
        this.trunkvolume = trunkvolume;
        this.enabled = enabled;
        this.seats = seats;
        this.consumption = consumption;
        this.transimissionType = transimissionType;
        this.engineType = engineType;
        this.description = description;
        this.photo = photo;
        this.carCategory = carCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getTrunkvolume() {
        return trunkvolume;
    }

    public void setTrunkvolume(Double trunkvolume) {
        this.trunkvolume = trunkvolume;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    public TransmissionType getTransimissionType() {
        return transimissionType;
    }

    public void setTransimissionType(TransmissionType transimissionType) {
        this.transimissionType = transimissionType;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
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

    public List<Carorder> getOrderss() {
        return orderss;
    }

    public void setOrderss(List<Carorder> orderss) {
        this.orderss = orderss;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", model=" + model + ", brand=" + brand + ", baseprice=" + baseprice + ", color=" + color + ", power=" + power + ", productionyear=" + productionyear + ", trunkvolume=" + trunkvolume + ", enabled=" + enabled + ", seats=" + seats + ", consumption=" + consumption + ", transimissionType=" + transimissionType + ", engineType=" + engineType + ", description=" + description + ", photo=" + photo + ", carCategory=" + carCategory + ", orderss=" + orderss + '}';
    }
}
