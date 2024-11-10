package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarsController {

    @GetMapping("/cars")
    public String carsPage(@RequestParam(value = "count", required = false) Integer count){
        System.out.println("Count = " + count);
        return "cars/cars_table";
    }

}
