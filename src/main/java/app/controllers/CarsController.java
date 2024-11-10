package app.controllers;

import app.exceptions.DisabledFilterException;
import app.model.Car;
import app.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarsController {

    @Autowired
    private CarsService carsService;

    @Value("${maxCar}")
    private int maxCar;

    @Value("${sortByColor}")
    private boolean sortByColor;

    @Value("${sortByModel}")
    private boolean sortByModel;

    @GetMapping("/cars")
    public String carsPage(@RequestParam(value = "count", required = false) Integer count,
                           @RequestParam(value = "sortBy", required = false, defaultValue = "") String sortBy,
                           Model model) {

        if (sortBy.equals("Color") && !sortByColor || sortBy.equals("Model") && !sortByModel) {
            throw new DisabledFilterException("You've tried to use disabled filter");
        }

        if (count == null || count > maxCar) {
            if (!sortBy.isEmpty()) {
                model.addAttribute("cars", carsService.listCars(sortBy));
            } else {
                model.addAttribute("cars", carsService.listCars());
            }
        } else {
            if (!sortBy.isEmpty()) {
                model.addAttribute("cars", carsService.listCars(count, sortBy));
            } else {
                model.addAttribute("cars", carsService.listCars(count));
            }

        }
        return "cars/cars_table";
    }

    @GetMapping("/filldb")
    public String fillDBPage() {
        carsService.add(new Car("Bmw", "black"));
        carsService.add(new Car("Volvo", "white"));
        carsService.add(new Car("Opel", "red"));
        carsService.add(new Car("Audi", "green"));
        carsService.add(new Car("Nissan", "yellow"));
        return "cars/cars_added_info";
    }

    @ExceptionHandler(DisabledFilterException.class)
    public ResponseEntity<String> handleBadRequest(DisabledFilterException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
