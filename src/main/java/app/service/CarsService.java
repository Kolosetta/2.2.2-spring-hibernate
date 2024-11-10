package app.service;


import app.model.Car;

import java.util.List;

public interface CarsService {
    void add(Car car);

    List<Car> listCars();

    List<Car> listCars(int count);
}
