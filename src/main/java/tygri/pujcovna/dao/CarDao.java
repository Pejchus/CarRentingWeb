package tygri.pujcovna.dao;

import tygri.pujcovna.model.Car;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CarDao extends BaseDao {

    public CarDao() {
        super(Car.class);
    }

    public boolean CreateCar(String model, String brand, double baseprice, String color, double power, int productionyear, double trunkvolume, boolean foldingrearseats, int seats, double consumption, String description, Byte[] photo) {
        try {
            Car car = new Car(model, brand, baseprice, color, power, productionyear, trunkvolume, foldingrearseats, seats, consumption, description, photo);
            persist(car);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
