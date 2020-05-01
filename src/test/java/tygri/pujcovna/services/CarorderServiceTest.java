package tygri.pujcovna.services;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tygri.pujcovna.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarorderServiceTest {

    @Autowired
    private UserService us;

    @Test
    public void test() {
        User user = us.createUser("zakaznik2", "zakaznik2", "zakaznik2@zakaznik.com", "true", "555555554", "05689", "zakaznik2", "vonZakaznik2", "Ricany2", "U nadrazi2", "62", "CUSTOMER");
        assertTrue(user != null);
    }
}
