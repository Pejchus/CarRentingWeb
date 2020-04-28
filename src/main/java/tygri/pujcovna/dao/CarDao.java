package tygri.pujcovna.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import tygri.pujcovna.model.Car;
import tygri.pujcovna.model.CarCategory;
import tygri.pujcovna.model.EngineType;
import tygri.pujcovna.model.TransmissionType;

@Repository
public class CarDao extends BaseDao {

    public CarDao() {
        super(Car.class);
    }

    public boolean CreateCar(String model, String brand, double baseprice, String color, double power, int productionyear, double trunkvolume, boolean enabled, int seats, double consumption, TransmissionType transimissionType, EngineType engineType, String description, Byte[] photo, CarCategory carCategory) {
        try {
            Car car = new Car(model, brand, baseprice, color, power, productionyear, trunkvolume, enabled, seats, consumption, transimissionType, engineType, description, photo, carCategory);
            persist(car);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean CreateCar(String model, String brand, double baseprice, String color, double power, int productionyear, double trunkvolume, boolean enabled, int seats, double consumption, TransmissionType transimissionType, EngineType engineType, String description, CarCategory carCategory) {
        try {
            Car car = new Car(model, brand, baseprice, color, power, productionyear, trunkvolume, enabled, seats, consumption, transimissionType, engineType, description, carCategory);
            persist(car);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteCar(int id) {
        try {
            Car toDelete = em.find(Car.class, id);
            super.remove(toDelete);

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Car> getFilteredCars(String model, String brand, double lowest, double highest, CarCategory carCategory, String color) {
        model = "%" + model + "%";
        brand = "%" + brand + "%";
        try {
            TypedQuery<Car> q = em.createQuery("SELECT e FROM Car e WHERE e.model LIKE :model and e.brand LIKE :brand and e.color like :color and e.carCategory LIKE :carCategory and e.baseprice between :lowest AND :highest", Car.class);
            return q.setParameter("lowest", lowest).setParameter("highest", highest).setParameter("model", model).setParameter("color", color).setParameter("brand", brand).setParameter("carCategory", carCategory).getResultList();
        } catch (RuntimeException e) {
            return null;
        }
    }

    public List<Car> getFilteredCars(String model, String brand, double lowest, double highest) {
        model = "%" + model + "%";
        brand = "%" + brand + "%";
        try {
            TypedQuery<Car> q = em.createQuery("SELECT e FROM Car e WHERE e.model LIKE :model and e.brand LIKE :brand and e.baseprice between :lowest AND :highest", Car.class);
            return q.setParameter("lowest", lowest).setParameter("highest", highest).setParameter("model", model).setParameter("brand", brand).getResultList();
        } catch (RuntimeException e) {
            return null;
        }
    }

    public List<Car> getCarsOnFrontPage() {
        try {
            TypedQuery<Car> q = em.createQuery("SELECT e FROM Car e WHERE e.onFrontPage=TRUE", Car.class);
            return q.getResultList();
        } catch (RuntimeException e) {
            return null;
        }
    }

    public Car getCarById(Integer id) {
        try {
            return (Car) find(id);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean setPhoto(Byte[] photo, Car car) {
        car.setPhoto(photo);
        try {
            em.merge(car);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeCar(Car car) {
        try {
            car.getOrderss().clear();
            super.remove(car);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean setEnabled(boolean b, Integer id) {
        try {
            Car car = getCarById(id);
            car.setEnabled(b);
            super.updateEntity(car);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean getIsOnFrontPage(Car car) {
        return car.isOnFrontPage();
    }

    public boolean setIsOnFrontPage(Car car, boolean b) {
        try {
            car.setOnFrontPage(b);
            updateEntity(car);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
