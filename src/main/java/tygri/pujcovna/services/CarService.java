package tygri.pujcovna.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tygri.pujcovna.dao.CarDao;
import tygri.pujcovna.model.Car;
import tygri.pujcovna.model.CarCategory;
import tygri.pujcovna.model.EngineType;
import tygri.pujcovna.model.TransmissionType;
import tygri.pujcovna.other.Constants;

@Service
public class CarService {

    private final CarDao carDao;
    private final Constants constants;

    @Autowired
    public CarService(CarDao carDao, Constants constants) {
        this.carDao = carDao;
        this.constants = constants;
    }

    //osklive, predelat jestli bude cas
    @Transactional
    public String getAllCarsPreviews() {
        return getCarsPreviews(carDao.getAll());
    }

    @Transactional
    public String getCarsPreviews(List<Car> cars) {
        StringBuilder sb = new StringBuilder(10000);
        for (Object obj : cars) {
            String previewString = constants.getCarPreview();
            Car car = (Car) obj;
            Byte[] carPhoto = car.getPhoto();
            String photoData = "";
            if (carPhoto != null) {
                byte[] photobytes = new byte[carPhoto.length];
                int i = 0;
                for (Byte b : carPhoto) {
                    photobytes[i++] = b;
                }
                photoData = Base64.getEncoder().encodeToString(photobytes);
            }
            previewString = previewString.replaceAll(";carProfileLink;", "carProfile?id=" + car.getId());
            previewString = previewString.replaceFirst(";carModel;", car.getModel());
            previewString = previewString.replaceFirst(";carCompany;", car.getBrand());
            previewString = previewString.replaceFirst(";carSeatNumber;", car.getSeats().toString());
            previewString = previewString.replaceFirst(";carPrice;", car.getBaseprice().toString());
            previewString = previewString.replaceFirst(";carPhotoData;", photoData);
            previewString = previewString.replaceFirst(";type;", car.getCarCategory().toString());
            previewString = previewString.replaceFirst(";enginetype;", car.getEngineType().toString());
            previewString = previewString.replaceFirst(";transmissiontype;", car.getTransimissionType().toString());
            previewString = previewString.replaceFirst(";productionyear;", car.getProductionyear().toString());
            previewString = previewString.replaceFirst(";power;", car.getPower().toString());
            sb.append(previewString);
        }
        return sb.toString();
    }

    @Transactional
    public boolean createCar(String model, String brand, String baseprice, String color, String power, String productionyear, String trunkvolume, String foldingrearseats, String seats, String consumption, String transimissionType, String engineType, String description, MultipartFile photo, String carCategory) {
        try {
            boolean foldingseats;
            if ("yes".equals(foldingrearseats)) {
                foldingseats = true;
            } else if ("no".equals(foldingrearseats)) {
                foldingseats = false;
            } else {
                return false;
            }
            if (photo != null) {

                Byte[] photoCopy = new Byte[photo.getBytes().length];
                int i = 0;
                for (Byte b : photo.getBytes()) {
                    photoCopy[i++] = b;
                }
                return carDao.CreateCar(model, brand, Double.valueOf(baseprice), color, Double.valueOf(power), Integer.valueOf(productionyear), Double.valueOf(trunkvolume), foldingseats, Integer.valueOf(seats), Double.valueOf(consumption), TransmissionType.valueOf(transimissionType), EngineType.valueOf(engineType), description, photoCopy, CarCategory.valueOf(carCategory));
            } else {
                return carDao.CreateCar(model, brand, Double.valueOf(baseprice), color, Double.valueOf(power), Integer.valueOf(productionyear), Double.valueOf(trunkvolume), foldingseats, Integer.valueOf(seats), Double.valueOf(consumption), TransmissionType.valueOf(transimissionType), EngineType.valueOf(engineType), description, CarCategory.valueOf(carCategory));
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Velky spatny: ");
            System.out.println(e);
            return false;
        }
    }

    @Transactional
    public List<Car> getFilteredCars(String color, String brand, String lowest, String highest) {
        double low;
        double high;
        try {
            low = Double.valueOf(lowest);
        } catch (NumberFormatException e) {
            low = 0;
        }
        try {
            high = Double.valueOf(highest);
        } catch (NumberFormatException e) {
            high = Double.MAX_VALUE;
        }
        return carDao.getFilteredCars(color, brand, low, high);
    }

    public Car getCarById(String id) {
        try {
            return carDao.getCarById(Integer.valueOf(id));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public String getPhoto(Car car) {
        Byte[] photo = car.getPhoto();
        if (photo == null) {
            return "<img src=\"data:image/png;base64," + " " + "\" alt=\"Foto auta\" height=\"100\" width=\"100\"/>";
        }
        byte[] photobytes = new byte[photo.length];
        int i = 0;
        for (Byte b : photo) {
            photobytes[i++] = b;
        }
        String photoData = Base64.getEncoder().encodeToString(photobytes);
        return "<img src=\"data:image/png;base64," + photoData + "\" alt=\"Foto auta\" height=\"100\" width=\"100\"/>";
    }

    public boolean setPhoto(Car car, MultipartFile photo) {
        try {
            Byte[] photoCopy = new Byte[photo.getBytes().length];
            int i = 0;
            for (Byte b : photo.getBytes()) {
                photoCopy[i++] = b;
            }
            System.out.println(car);
            System.out.println(Arrays.toString(photoCopy));
            return carDao.setPhoto(photoCopy, car);
        } catch (IOException e) {
            System.out.println("photo upload fail");
            return false;
        }
    }

    public Car getACar() {
        return (Car) carDao.getAll().get(0);
    }
}
