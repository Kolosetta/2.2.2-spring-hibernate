package app.controllers;

import app.exceptions.DisabledFilterException;
import app.model.Car;
import app.model.User;
import app.services.car.CarFilterService;
import app.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarController {

    @Autowired
    private CarFilterService carFilterService;

    @Autowired
    private UserService userService;

    @GetMapping("/cars")
    public String carsPage(@RequestParam(value = "count", required = false) Integer count, @RequestParam(value = "sortBy", required = false) String sortBy, Model model) {
        model.addAttribute("cars", carFilterService.getCarListByParameters(count, sortBy));
        return "cars/cars_table";
    }

    @GetMapping("/filldb")
    public String fillDBPage() {
        userService.add(new User("name1", "surname1", "email1", new Car("Bmw", "black", 2000000)));
        userService.add(new User("name2", "surname2", "email2", new Car("Volvo", "white", 3000000)));
        userService.add(new User("name3", "surname3", "email3", new Car("Mazda", "yellow", 1000000)));
        userService.add(new User("name4", "surname4", "email4", new Car("Nissan", "red", 50000)));
        userService.add(new User("name5", "surname5", "email5", new Car("Dodge", "blue", 700000)));
        return "cars/cars_added_info";
    }

    @ExceptionHandler(DisabledFilterException.class)
    public ResponseEntity<String> handleBadRequest(DisabledFilterException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
