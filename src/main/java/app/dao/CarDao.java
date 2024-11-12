package app.dao;



import app.model.Car;

import java.util.List;

public interface CarDao {
    void add(Car user);

    List<Car> listCars();

    List<Car> listCars(String sortBy);

    List<Car> listCars(int count);

    List<Car> listCars(int count, String sortBy);

}
