package tygri.pujcovna.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import tygri.pujcovna.dao.CarDao;
import tygri.pujcovna.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CarService {

    private final CarDao carDao;

    @Autowired
    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    //osklive, predelat jestli bude cas
    @Transactional
    public String getAllCars() {
        StringBuilder sb = new StringBuilder(10000);
        for (Object obj : carDao.getAll()) {
            Car car = (Car) obj;
            byte[] photobytes = new byte[car.getPhoto().length];
            int i = 0;
            for (Byte b : car.getPhoto()) {
                photobytes[i++] = b;
            }
            String photoData = Base64.getEncoder().encodeToString(photobytes);
            sb.append("<p>").append(car.toString()).append("</p><img src=\"data:image/png;base64,").append(photoData).append("\" alt=\"Foto auta\" height=\"100\" width=\"100\"/><br>");
        }
        return sb.toString();
    }

    @Transactional
    public boolean createCar(String model, String brand, String baseprice, String color, String power, String productionyear, String trunkvolume, String foldingrearseats, String seats, String consumption, String description, MultipartFile photo) {
        try {
            boolean foldingseats;
            if ("yes".equals(foldingrearseats)) {
                foldingseats = true;
            } else if ("no".equals(foldingrearseats)) {
                foldingseats = false;
            } else {
                return false;
            }
            Byte[] photoCopy = new Byte[photo.getBytes().length];
            int i = 0;
            for (Byte b : photo.getBytes()) {
                photoCopy[i++] = b;
            }
            return carDao.CreateCar(model, brand, Double.valueOf(baseprice), color, Double.valueOf(power), Integer.valueOf(productionyear), Double.valueOf(trunkvolume), foldingseats, Integer.valueOf(seats), Double.valueOf(consumption), description, photoCopy);
        } catch (NumberFormatException | IOException e) {
            System.out.println("Velky spatny");
            return false;
        }
    }

    @Transactional
    public List<Car> getFilteredCars(String color, String brand, String lowest, String highest) {
        double low;
        double high;
        try {
            low = Double.valueOf(lowest);
        } catch (Exception e) {
            low = 0;
        }
        try {
            high = Double.valueOf(highest);
        } catch (Exception e) {
            high = Double.MAX_VALUE;
        }
        return carDao.getFilteredCars(color, brand, low, high);
    }
}
