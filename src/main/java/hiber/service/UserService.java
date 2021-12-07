package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();

    // добавляем метод, поиск user по model и series
    User getUser(String model, int series);
}
