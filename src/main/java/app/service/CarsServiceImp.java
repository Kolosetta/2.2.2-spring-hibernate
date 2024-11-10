package app.service;

import app.dao.CarDao;
import app.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarsServiceImp implements CarsService {

    @Autowired
    private CarDao carDao;

    @Transactional
    @Override
    public void add(Car user) {
        carDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> listCars() {
        return carDao.listCars();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> listCars(int count) {
        return carDao.listCars(count);
    }

}
