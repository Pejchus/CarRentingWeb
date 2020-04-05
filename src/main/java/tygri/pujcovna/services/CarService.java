package tygri.pujcovna.services;

import tygri.pujcovna.dao.CarDao;
import tygri.pujcovna.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CarService {

    private final CarDao carDao;

    @Autowired
    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    @Transactional
    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }

    @Transactional
    public boolean createCar(String model, String brand, String baseprice, String color, String power, String productionyear, String trunkvolume, String foldingrearseats, String seats, String consumption, String description) {
        try {
            boolean foldingseats;
            if ("yes".equals(foldingrearseats)) {
                foldingseats = true;
            } else if ("no".equals(foldingrearseats)) {
                foldingseats = false;
            } else {
                return false;
            }
            return carDao.CreateCar(model, brand, Double.valueOf(baseprice), color, Double.valueOf(power), Integer.valueOf(productionyear), Double.valueOf(trunkvolume), foldingseats, Integer.valueOf(seats), Double.valueOf(consumption), description);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
