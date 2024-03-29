package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface CarService {
    void add(Car car);

    List<Car> listCars();

    // добавляем метод, поиск user по model и series
    User getUser(String model, int series);
}
