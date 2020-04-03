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

    public boolean addCar(String name, String description, String model) {
        return carDao.CreateCar(name, description, model);
    }
}
