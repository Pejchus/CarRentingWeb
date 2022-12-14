package tygri.pujcovna;

import java.sql.Timestamp;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tygri.pujcovna.dao.CarorderDao;
import tygri.pujcovna.model.AuthorityType;
import tygri.pujcovna.model.Car;
import tygri.pujcovna.model.User;
import tygri.pujcovna.services.CarService;
import tygri.pujcovna.services.UserService;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    ApplicationRunner init(UserService userService, CarService carService, CarorderDao carorderDao) {
        return (ApplicationArguments args) -> dataSetup(userService, carService, carorderDao);
    }

    //init db on launch, works only when db is empty, this is executed before any tests
    public void dataSetup(UserService userService, CarService carService, CarorderDao carorderDao) {
        userService.createAuthority(AuthorityType.ROLE_CUSTOMER);
        userService.createAuthority(AuthorityType.ROLE_ADMIN);
        userService.createAuthority(AuthorityType.ROLE_EMPLOYEE);
        userService.createUser("admin", "admin", "admin@admin.com", "true", "777777777", "25101", "admin", "vonAdmin", "Praha", "Parizska", "4", "ADMIN");
        userService.createUser("zamestnanec", "zamestnanec", "zamestnanec@zamestnanec.com", "true", "666666666", "069", "zamestnanec", "vonZamestnanec", "Ricany", "Sokolska", "5", "EMPLOYEE");
        userService.createUser("zakaznik", "zakaznik", "zakaznik@zakaznik.com", "true", "555555555", "05689", "zakaznik", "vonZakaznik", "Ricany", "U nadrazi", "6", "CUSTOMER");
        carService.createCar("Fabia", "Skoda", "1100", "silver", "55", "2020", "420", "5", "5.5", "AUTOMATIC", "ELECTRIC", "pekny", null, "SEDAN");
        carService.createCar("Felicia", "Skoda", "1000", "blue", "55", "1999", "420", "5", "4.5", "MANUAL", "GAS", "nic moc", null, "SEDAN");
        carService.createCar("Octavia", "Skoda", "1250", "silver", "80", "2015", "420", "5", "5.5", "AUTOMATIC", "DIESEL", "hezky", null, "SEDAN");
        carService.createCar("Superb", "Skoda", "2000", "silver", "120", "2020", "420", "5", "5.5", "AUTOMATIC", "ELECTRIC", "pekny", null, "SEDAN");
        carService.createCar("Superb", "Skoda", "2000", "red", "100", "2020", "420", "5", "5.5", "AUTOMATIC", "DIESEL", "pekny", null, "SEDAN");
        carService.createCar("x6", "BMW", "2000", "black", "220", "2020", "420", "5", "5.5", "AUTOMATIC", "ELECTRIC", "pekny", null, "SEDAN");
        carService.createCar("x5", "BMW", "2000", "blue", "200", "2020", "420", "5", "5.5", "AUTOMATIC", "ELECTRIC", "pekny", null, "SEDAN");
        carService.createCar("m2", "BMW", "2000", "red", "230", "2020", "420", "5", "5.5", "AUTOMATIC", "ELECTRIC", "pekny", null, "SEDAN");
        carService.createCar("A4", "Audi", "2000", "green", "2000", "2012", "425", "5", "6.5", "AUTOMATIC", "GAS", "docela dobry", null, "SEDAN");
        carService.createCar("Superb", "Skoda", "2000", "orange", "120", "2014", "420", "5", "5.5", "AUTOMATIC", "ELECTRIC", "pekny", null, "SEDAN");
        carService.createCar("Superb", "Skoda", "2000", "yellow", "120", "2017", "420", "5", "5.5", "AUTOMATIC", "ELECTRIC", "pekny", null, "SEDAN");
        carService.createCar("nakejSaab", "Saab", "2000", "green", "2000", "2012", "425", "5", "6.5", "MANUAL", "DIESEL", "docela ujde", null, "CABRIOLET");
        Car car = carService.getACar();
        User user = userService.loadUserByUsername("zakaznik");
        carorderDao.createCarorder(user, new Timestamp(System.currentTimeMillis() - 86400000 * 12), new Timestamp(System.currentTimeMillis() - 86400000 * 8), car, car.getBaseprice(), true);
        carorderDao.createCarorder(user, new Timestamp(System.currentTimeMillis() + 86400000 * 3), new Timestamp(System.currentTimeMillis() + 86400000 * 8), car, car.getBaseprice(), true);
        carorderDao.createCarorder(user, new Timestamp(System.currentTimeMillis() + 86400000 * 15), new Timestamp(System.currentTimeMillis() + 86400000 * 21), car, car.getBaseprice(), true);

    }
}
