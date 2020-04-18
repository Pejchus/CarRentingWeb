package tygri.pujcovna.dao;

import org.springframework.stereotype.Repository;
import tygri.pujcovna.model.Car;
import tygri.pujcovna.model.Carorder;
import tygri.pujcovna.model.User;

import java.sql.Timestamp;
import java.time.DateTimeException;
import java.util.List;
import java.util.Date;

@Repository
public class CarorderDao extends BaseDao {

    public CarorderDao() {
        super(Carorder.class);
    }

    public List<Car> getAllCarorders() {
        return super.getAll();
    }

    public boolean createCarorder(User customer, Timestamp begin,Timestamp end,Car car,double price,boolean paid ){
        try{
            Carorder order = new Carorder(car,begin,end,new Timestamp(System.currentTimeMillis()),price,paid);
            persist(order);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
