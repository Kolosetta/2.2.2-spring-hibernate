package app.dao;

import app.model.Car;

import java.util.List;

public interface CarDao {
    void add(Car user);

    List<Car> listCars();

    List<Car> listCars(int count);

}
