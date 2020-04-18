package tygri.pujcovna.dao;

import tygri.pujcovna.model.Car;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CarDao extends BaseDao {

    public CarDao() {
        super(Car.class);
    }

    public List<Car> getAllCars() {
        return super.getAll();
    }

    public boolean CreateCar(String model, String brand, double baseprice, String color, double power, int productionyear, double trunkvolume, boolean foldingrearseats, int seats, double consumption, String description) {
        try {
            Car car = new Car(model, brand, baseprice, color, power, productionyear, trunkvolume, foldingrearseats, seats, consumption, description);
            persist(car);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteCar (int id){
        try {
            Car toDelete = em.find(Car.class,id);
            super.remove(toDelete);

        } catch (Exception e){
            throw new RuntimeException("car with id "+id+" not found");
        }
        return true;
    }
    public List<Car> getFilteredCars(String color,String brand,double lowest, double highest){
        color = "%"+color+"%";
        brand ="%"+brand+"%";
        try {
            return em.createQuery("SELECT e FROM Car e WHERE e.color LIKE :color and e.brand LIKE :brand and e.baseprice between :lowest AND :highest", Car.class).setParameter("lowest",lowest).setParameter("highest",highest).setParameter("color",color).setParameter("brand",brand).getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException("No car with that color");
        }
    }

}
