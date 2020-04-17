package tygri.pujcovna.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = "CARORDER")
public class Carorder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User account;

    @ManyToOne
    private Car car;

    @Basic(optional = false)
    @Column(nullable = false)
    private Timestamp begindate;

    @Basic(optional = false)
    @Column(nullable = false)
    private Timestamp enddate;

    @Basic(optional = false)
    @Column(nullable = false)
    private Timestamp createDate;

    @Basic(optional = false)
    @Column(nullable = false)
    private double price;

    @Basic(optional = false)
    @Column(nullable = false)
    private boolean paid;

    public Carorder(Car car, Timestamp begindate, Timestamp enddate, Timestamp createDate, double price, boolean paid) {
        this.car = car;
        this.begindate = begindate;
        this.enddate = enddate;
        this.createDate = createDate;
        this.price = price;
        this.paid = paid;
    }

    public void setAccount(User account) {
        this.account = account;
    }

    public User getAccount() {
        return account;
    }

    public Integer getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Timestamp getBegindate() {
        return begindate;
    }

    public void setBegindate(Timestamp begindate) {
        this.begindate = begindate;
    }

    public Timestamp getEnddate() {
        return enddate;
    }

    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Carorder{" + "id=" + id + ", car=" + car + ", begin=" + begindate + ", end=" + enddate + ", createDate=" + createDate + ", price=" + price + ", paid=" + paid + '}';
    }
}
