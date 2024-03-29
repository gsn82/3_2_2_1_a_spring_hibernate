package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface CarDao {
    void add(Car car);

    List<Car> listUsers();

    // добавляем метод, поиск user по model и series
    User getUser(String model, int series);
}
