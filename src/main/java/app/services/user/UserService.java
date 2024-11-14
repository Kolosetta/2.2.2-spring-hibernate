package app.services.user;


import app.model.Car;
import app.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    User getUserByCar(Car car);

    User getUserById(int id);

}
