package tygri.pujcovna.dao;

import java.sql.Timestamp;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tygri.pujcovna.model.AuthorityType;
import tygri.pujcovna.model.Car;
import tygri.pujcovna.model.CarCategory;
import tygri.pujcovna.model.Carorder;
import tygri.pujcovna.model.EngineType;
import tygri.pujcovna.model.TransmissionType;
import tygri.pujcovna.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarorderDaoTest {

    @Autowired
    private CarorderDao carorderDao;

    @Autowired
    private UserAndAuthorityDao userAndAuthorityDao;

    @Autowired
    private CarDao carDao;

    @Test
    public void carorderTest() {
        // given
        Car car = carDao.CreateCar("mode", "brand", 1200.0, "color", 120.0, 1999, 320.0, true, 4, 6.5, TransmissionType.AUTOMATIC, EngineType.DIESEL, "popis", CarCategory.CABRIOLET);
        User account = userAndAuthorityDao.create("username", "password", "email", true, "phone", "countryCode", "firstname", "lastname", "city", "street", "streetno", AuthorityType.ROLE_CUSTOMER);
        Carorder carorder = carorderDao.createCarorder(account, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), car, car.getBaseprice(), true);

        // when
        Carorder found = (Carorder) carorderDao.find(carorder.getId());

        // then
        assertThat(found.getAccount().getUsername().equals(carorder.getAccount().getUsername()));
    }
}
