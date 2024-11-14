package app.services.car;

import app.exceptions.DisabledFilterException;
import app.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarFilterService {

    @Value("${maxCar}")
    private int maxCar;

    @Value("#{'${disabledFiltersList}'.split(',')}")
    private List<String> disabledFilters;

    @Autowired
    private CarService carService;

    public List<Car> getCarListByParameters(Integer count, String sortBy) {
        if (disabledFilters.contains(sortBy)) {
            throw new DisabledFilterException("You've tried to use disabled filter");
        }

        if (count == null || count > maxCar) {
            if (sortBy != null) {
                return carService.listCars(sortBy);
            } else {
                return carService.listCars();
            }
        } else {
            if (sortBy != null) {
                return carService.listCars(count, sortBy);
            } else {
                return carService.listCars(count);
            }
        }
    }
}
