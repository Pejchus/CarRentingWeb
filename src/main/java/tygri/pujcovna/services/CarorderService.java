package tygri.pujcovna.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tygri.pujcovna.dao.CarDao;
import tygri.pujcovna.dao.CarorderDao;
import tygri.pujcovna.model.Car;
import tygri.pujcovna.model.CarCategory;
import tygri.pujcovna.model.Carorder;
import tygri.pujcovna.model.User;

@Service
public class CarorderService {

    @Autowired
    private CarorderDao carOrderDao;

    @Autowired
    private CarService carService;

    @Autowired
    private CarDao carDao;

    public String getOffers(String modelsearch, String carcompany, String tripstart, String tripend, String range1a, String range1b, String type) {
        Double lowPrice;
        Double highPrice;
        if ("".equals(range1a)) {
            lowPrice = Double.MIN_VALUE;
        } else {
            lowPrice = Double.valueOf(range1a);
        }
        if ("".equals(range1b)) {
            highPrice = Double.MAX_VALUE;
        } else {
            highPrice = Double.valueOf(range1b);
        }
        Timestamp startDate = null;
        Timestamp endDate = null;
        if ("vsechny".equals(carcompany)) {
            carcompany = "";
        }
        if (!"".equals(tripstart)) {
            startDate = Timestamp.valueOf(tripstart + " 00:00:00");
        }
        if (!"".equals(tripend)) {
            endDate = Timestamp.valueOf(tripend + " 00:00:00");
        }
        if ("all".equals(type)) {
            type = "";
        }
        List<Car> filteredCars;
        if (!"".equals(type)) {
            filteredCars = carDao.getFilteredCars(modelsearch, carcompany, lowPrice, highPrice, CarCategory.valueOf(type));
        } else {
            filteredCars = carDao.getFilteredCars(modelsearch, carcompany, lowPrice, highPrice);
        }
        List<Car> freeCars = new ArrayList<>();
        for (Car car : filteredCars) {
            if (isFree(car, startDate, endDate)) {
                freeCars.add(car);
            }
        }
        return carService.getCarsPreviews(freeCars);
    }

    private boolean isFree(Car car, Timestamp startDate, Timestamp endDate) {
        if (startDate == null && endDate == null) {
            return true;
        } else if (startDate == null) {
            if (carOrderDao.getReservationsUpTo(car, endDate).isEmpty()) {
                return true;
            }
        } else if (endDate == null) {
            if (carOrderDao.getReservationsFrom(car, startDate).isEmpty()) {
                return true;
            }
        } else {
            if (carOrderDao.getReservations(car, startDate, endDate).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean createOrder(User user, Car car, Timestamp begin, Timestamp end) {
        return carOrderDao.createCarorder(user, begin, end, car, car.getBaseprice(), true);
    }

    public List<Carorder> getAllOrders(User user) {
        return carOrderDao.getUserOrderHistory(user);
    }

}
