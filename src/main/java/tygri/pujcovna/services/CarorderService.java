package tygri.pujcovna.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tygri.pujcovna.dao.CarDao;
import tygri.pujcovna.dao.CarorderDao;

@Service
public class CarorderService {

    @Autowired
    private CarorderDao carOrderDao;

    @Autowired
    private CarDao carDao;

    public String getOffers(String modelsearch, String carcompany, String tripstart, String tripend, String range1a, String range1b) {
        return carDao.getAll().toString();
    }

}
