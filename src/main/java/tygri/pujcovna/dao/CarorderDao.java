package tygri.pujcovna.dao;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.stereotype.Repository;
import tygri.pujcovna.model.Car;
import tygri.pujcovna.model.Carorder;
import tygri.pujcovna.model.User;

@Repository
public class CarorderDao extends BaseDao {

    public CarorderDao() {
        super(Carorder.class);
    }

    public List<Carorder> getAllCarorders() {
        return super.getAll();
    }

    public boolean createCarorder(User customer, Timestamp begin, Timestamp end, Car car, double price, boolean paid) {
        try {
            Carorder order = new Carorder(customer, car, begin, end, new Timestamp(System.currentTimeMillis()), price, paid);
            persist(order);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Carorder> getUserOrderHistory(User u) {
        try {
            return em.createQuery("SELECT e FROM Carorder e where e.account=:user").setParameter("user", u).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Carorder> getCarOrderHistory(Car c) {
        try {
            return em.createQuery("SELECT e FROM Carorder e where e.car=:car and e.paid=true").setParameter("car", c).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Carorder> getUserCurrentReservations(User u) {
        try {
            return em.createQuery("SELECT e FROM Carorder e where e.account=:user and e.enddate>:date").setParameter("date", new Timestamp(System.currentTimeMillis())).setParameter("user", u).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Carorder> getCarCurrentReservations(Car c) {
        try {
            return em.createQuery("SELECT e FROM Carorder e where e.car=:car and e.enddate>:date").setParameter("date", new Timestamp(System.currentTimeMillis())).setParameter("car", c).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get reservations intersecting in dates (startDate, endDate) interval
     *
     * @param car
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Carorder> getReservations(Car car, Timestamp startDate, Timestamp endDate) {
        try {
            return em.createQuery("SELECT e FROM Carorder e where e.car=:car and (e.enddate>:startDate or e.begindate<:endDate)").setParameter("startDate", startDate).setParameter("endDate", endDate).setParameter("car", car).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get reservations that end after startDate
     *
     * @param car
     * @param startDate
     * @return
     */
    public List<Carorder> getReservationsFrom(Car car, Timestamp startDate) {
        try {
            return em.createQuery("SELECT e FROM Carorder e where e.car=:car and e.enddate>:startDate").setParameter("startDate", startDate).setParameter("car", car).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get reservations that begin before endDate
     *
     * @param car
     * @param endDate
     * @return
     */
    public List<Carorder> getReservationsUpTo(Car car, Timestamp endDate) {
        try {
            return em.createQuery("SELECT e FROM Carorder e where e.car=:car and e.begindate<:endDate").setParameter("endDate", endDate).setParameter("car", car).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
