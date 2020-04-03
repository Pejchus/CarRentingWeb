package tygri.pujcovna.dao;

import tygri.pujcovna.model.Car;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CarDao extends BaseDao {

    public CarDao() {
        super(Car.class);
    }

    public List<Car> getAllCars() {
        return super.getAll();
    }

    public boolean CreateCar(String name, String description, String model) {
        Car car = new Car(name, description, model);
        try {
            persist(car);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
