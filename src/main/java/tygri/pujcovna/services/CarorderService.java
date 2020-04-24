package tygri.pujcovna.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tygri.pujcovna.dao.CarDao;
import tygri.pujcovna.dao.CarorderDao;
import tygri.pujcovna.model.Car;

@Service
public class CarorderService {

    @Autowired
    private CarorderDao carOrderDao;

    @Autowired
    private CarDao carDao;

    public String getOffers(String modelsearch, String carcompany, String tripstart, String tripend, String range1a, String range1b) {
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
        Date startDate = null;
        Date endDate = null;
        if ("vsechny".equals(carcompany)) {
            carcompany = "";
        }
        if (!"".equals(tripstart)) {
            startDate = new Date(tripstart);
        }
        if (!"".equals(tripend)) {
            endDate = new Date(tripend);
        }

        List<Car> filteredCars = carDao.getFilteredCars(modelsearch, carcompany, lowPrice, highPrice);
        List<Car> freeCars = new ArrayList<>();
        for (Car car : filteredCars) {
            if (isFree(car, startDate, endDate)) {
                freeCars.add(car);
            }
        }
        return freeCars.toString();
    }

    private boolean isFree(Car car, Date startDate, Date endDate) {
        if (startDate == null && endDate == null) {
            return true;
        } else if (startDate == null) {
            if (carOrderDao.getReservationsUpTo(car, endDate) == null) {
                return true;
            }
        } else if (endDate == null) {
            if (carOrderDao.getReservationsFrom(car, startDate) == null) {
                return true;
            }
        } else {
            if (carOrderDao.getReservations(car, startDate, endDate) == null) {
                return true;
            }
        }
        return false;
    }

}
