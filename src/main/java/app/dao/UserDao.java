package app.dao;


import app.model.Car;
import app.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> listUsers();

    User getUserByCar(Car car);

    User getUserById(int id);

}
