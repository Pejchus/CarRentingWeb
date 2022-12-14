package tygri.pujcovna.services;

import java.sql.Timestamp;
import java.util.*;

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

    public String getEnabledPrefferd(User user, String modelsearch, String carcompany, String tripstart, String tripend, String range1a, String range1b, String type, String pagestart) {
        int pagestartt = Integer.valueOf(pagestart);
        HashMap<String, Integer> colorHistory = new HashMap<>();
        HashMap<String, Integer> typeHistory = new HashMap<>();
        HashMap<String, Integer> companyHistory = new HashMap<>();
        List<Carorder> orders = carOrderDao.getUserOrderHistory(user);
        for (Carorder order : orders) {
            Car car = order.getCar();
            if (colorHistory.containsKey(car.getColor())) {
                colorHistory.put(car.getColor(), colorHistory.get(car.getColor()) + 1);
            } else {
                colorHistory.put(car.getColor(), 1);
            }
            if (typeHistory.containsKey(car.getCarCategory())) {
                typeHistory.put(String.valueOf(car.getCarCategory()), typeHistory.get(String.valueOf(car.getCarCategory())) + 1);
            } else {
                typeHistory.put(String.valueOf(car.getCarCategory()), 1);
            }
            if (companyHistory.containsKey(car.getModel())) {
                companyHistory.put(car.getModel(), companyHistory.get(car.getModel()) + 1);
            } else {
                companyHistory.put(car.getModel(), 1);
            }
        }
        Set<Car> preferedcars = new LinkedHashSet<>(9);
        String[][] topick = permutations(colorHistory, typeHistory, companyHistory);

        for (int i = 0; i < 9; i++) {
            if (topick[i][1] == null) {
                topick[i][1] = "all";
            }
            if (!modelsearch.equals("") && topick[i][2] != modelsearch) {
                continue;
            }
            if (topick[i][1] != type && (!type.equals("all"))) {
                continue;
            }
            List<Car> toadd = getEnabledFilteredCars(topick[i][2], carcompany, tripstart, tripend, range1a, range1b, topick[i][1], topick[i][0]);
            preferedcars.addAll(toadd);
        }
        preferedcars.addAll(getEnabledFilteredCars(modelsearch, carcompany, tripstart, tripend, range1a, range1b, type, ""));
        return cutToPreview(new ArrayList(preferedcars), pagestartt);

    }

    public String cutToPreview(List carlist, int pagestartt) {
        int pageend = pagestartt + 9;
        if (pageend > carlist.size()) {
            pageend = carlist.size();
        }
        if (pageend < pagestartt) {
            return carService.getCarsPreviews(new ArrayList<>());
        }
        return carService.getCarsPreviews(carlist.subList(pagestartt, pageend));
    }

    public String getEnabledCarsOffers(String modelsearch, String carcompany, String tripstart, String tripend, String range1a, String range1b, String type, String pagestart) {
        return cutToPreview(getEnabledFilteredCars(modelsearch, carcompany, tripstart, tripend, range1a, range1b, type, ""), Integer.valueOf(pagestart));
    }

    private List<Car> getEnabledFilteredCars(String modelsearch, String carcompany, String tripstart, String tripend, String range1a, String range1b, String type, String color) {
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
            filteredCars = carDao.getFilteredEnabledCars(modelsearch, carcompany, lowPrice, highPrice, CarCategory.valueOf(type), "");
        } else {
            filteredCars = carDao.getFilteredEnabledCars(modelsearch, carcompany, lowPrice, highPrice);
        }
        List<Car> freeCars = new ArrayList<>();
        for (Car car : filteredCars) {
            if (isFree(car, startDate, endDate)) {
                freeCars.add(car);
            }
        }
        return freeCars;
    }

    /*
    Zatim Lze objednavat alespon 0 hodin dopredu
     */
    private boolean isFree(Car car, Timestamp startDate, Timestamp endDate) {
        if (startDate == null && endDate == null) {
            return true;
        } else if (startDate == null) {
            if (endDate.after(new Timestamp(System.currentTimeMillis() - 86400000)) && carOrderDao.getReservationsUpTo(car, endDate).isEmpty()) {
                return true;
            }
        } else if (endDate == null) {
            if (startDate.after(new Timestamp(System.currentTimeMillis() - 86400000)) && carOrderDao.getReservationsFrom(car, startDate).isEmpty()) {
                return true;
            }
        } else {
            if (startDate.after(new Timestamp(System.currentTimeMillis() - 86400000)) && endDate.after(new Timestamp(System.currentTimeMillis() - 86400000)) && carOrderDao.getReservations(car, startDate, endDate).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public Carorder createOrder(User user, Car car, String begin, String end) {
        try {
            Timestamp beginDate = Timestamp.valueOf(begin + " 00:00:00");
            Timestamp endDate = Timestamp.valueOf(end + " 00:00:00");
            if (!beginDate.after(endDate) && isFree(car, beginDate, endDate)) {
                return carOrderDao.createCarorder(user, beginDate, endDate, car, car.getBaseprice(), true);
            } else {
                return null;
            }
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public List<Carorder> getAllOrders(User user) {
        return carOrderDao.getUserOrderHistory(user);
    }

    public List<Carorder> getAllOrders(Car car) {
        return carOrderDao.getCarOrderHistory(car);
    }

    private String[][] permutations(HashMap colorHistory, HashMap typeHistory, HashMap brandHistory) {
        String[][] per = new String[9][3];
        String[] topColor = topThree(colorHistory);
        String[] topType = topThree(typeHistory);
        String[] topBrand = topThree(brandHistory);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                per[j + i * 4][0] = topColor[i];
                per[j + i * 4][1] = topType[i];
                per[j + i * 4][2] = topBrand[i];
                if (j == 1) {
                    per[j + i * 4][0] = topColor[i + 1];
                }
                if (j == 2) {
                    per[j + i * 4][1] = topType[i + 1];
                }
                if (j == 3) {
                    per[j + i * 4][2] = topBrand[i + 1];
                }
                if (i == 2) {
                    break;
                }
            }
        }

        return per;
    }

    private String[] topThree(HashMap<String, Integer> map) {
        int first, second, third;
        first = second = third = -1;
        String[] res = new String[3];
        int i = 0;
        for (String name : map.keySet()) {
            if (map.get(name).intValue() > first) {
                third = second;
                second = first;
                first = map.get(name).intValue();
                res[2] = res[1];
                res[1] = res[0];
                res[0] = name;
            } else if (map.get(name).intValue() > second) {
                third = second;
                second = map.get(name).intValue();
                res[2] = res[1];
                res[1] = name;
            } else if (map.get(name).intValue() > third) {
                third = map.get(name).intValue();
                res[2] = name;
            }
        }
        return res;
    }

    public Double getLowestEnabledCarPrice() {
        return carDao.getLowestEnabledCarPrice();
    }

    public Double getHighestEnabledCarPrice() {
        return carDao.getHighestEnabledCarPrice();
    }

    public boolean deleteOrder(String userId, String orderID, boolean admin) {
        try {
            Carorder carOrder = carOrderDao.getOrder(orderID);
            if (System.currentTimeMillis() > carOrder.getBegindate().getTime() || !admin && !userId.equals(carOrder.getAccount().getId().toString())) {
                return false;
            }
            return carOrderDao.removeOrder(carOrderDao.getOrder(orderID));
        } catch (Exception e) {
            return false;
        }
    }

    public String getCarOrderOwner(String id) {
        return carOrderDao.getOrder(id).getAccount().getId().toString();
    }

    public CarorderDao getCarOrderDao() {
        return carOrderDao;
    }

    public Car getCarOrderCar(String orderId) {
        try {
            Carorder carOrder = carOrderDao.getOrder(orderId);
            return carOrder.getCar();
        } catch (Exception e) {
            return null;
        }
    }
}
