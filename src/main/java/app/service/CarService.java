package app.service;


import app.model.Car;

import java.util.List;

public interface CarService {
    void add(Car car);

    List<Car> listCars();

    List<Car> listCars(String sortBy);

    List<Car> listCars(int count);

    List<Car> listCars(int count, String sortBy);
}
