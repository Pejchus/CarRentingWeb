package tygri.pujcovna.dao;

import java.sql.Timestamp;
import java.util.Calendar;
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

    /**
     * Every order is created with 0's in time part of the begin and end
     * Timestamp
     *
     * @param customer
     * @param begin
     * @param end
     * @param car
     * @param price
     * @param paid
     * @return
     */
    public boolean createCarorder(User customer, Timestamp begin, Timestamp end, Car car, double price, boolean paid) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(begin.getTime());
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            begin.setTime(c.getTimeInMillis());
            c = Calendar.getInstance();
            c.setTimeInMillis(end.getTime());
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            end.setTime(c.getTimeInMillis());
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
            return em.createQuery("SELECT e FROM Carorder e where e.car=:car").setParameter("car", c).getResultList();
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
            return em.createQuery("SELECT e FROM Carorder e WHERE e.car=:car AND NOT (e.begindate>:endDate OR e.enddate<:startDate)").setParameter("startDate", startDate).setParameter("endDate", endDate).setParameter("car", car).getResultList();
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
            return em.createQuery("SELECT e FROM Carorder e where e.car=:car and e.enddate>=:startDate").setParameter("startDate", startDate).setParameter("car", car).getResultList();
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
            return em.createQuery("SELECT e FROM Carorder e where e.car=:car and e.begindate<=:endDate").setParameter("endDate", endDate).setParameter("car", car).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean setOrdersUser(Carorder order, User user) {
        try {
            System.out.println("remove");
            order.setAccount(user);
            updateEntity(order);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
