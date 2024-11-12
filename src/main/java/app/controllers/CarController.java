package app.controllers;

import app.exceptions.DisabledFilterException;
import app.model.Car;
import app.service.CarFilterService;
import app.service.CarService;
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
    private CarService carService;

    @GetMapping("/cars")
    public String carsPage(@RequestParam(value = "count", required = false) Integer count, @RequestParam(value = "sortBy", required = false) String sortBy, Model model) {

        model.addAttribute("cars", carFilterService.getCarListByParameters(count, sortBy));
        return "cars/cars_table";
    }

    @GetMapping("/filldb")
    public String fillDBPage() {
        carService.add(new Car("Bmw", "black"));
        carService.add(new Car("Volvo", "white"));
        carService.add(new Car("Opel", "red"));
        carService.add(new Car("Audi", "green"));
        carService.add(new Car("Nissan", "yellow"));
        return "cars/cars_added_info";
    }

    @ExceptionHandler(DisabledFilterException.class)
    public ResponseEntity<String> handleBadRequest(DisabledFilterException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
