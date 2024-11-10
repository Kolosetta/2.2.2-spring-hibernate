package app.service;


import app.model.Car;

import java.util.List;

public interface CarService {
    void add(Car car);

    List<Car> listCars();
}
