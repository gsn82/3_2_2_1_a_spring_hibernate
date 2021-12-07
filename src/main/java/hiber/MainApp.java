package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        // создаем user

        User user1 = new User("Ivan", "Ivanov", "user1@gmail.com");
        user1.setCar(new Car("Niva", 7777));

        User user2 = new User("Alex", "Petrov", "user2@gmail.com");
        user2.setCar(new Car("UAZ", 5555));

        User user3 = new User("Sergey", "Sidorov", "user3@gmail.com");
        user3.setCar(new Car("Fiat", 8888));

        User user4 = new User("Fedor", "Petrosan", "user4@gmail.com");
        user3.setCar(new Car("BMW", 9999));


        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        // добавляем user
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        // выводим на экран
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }


        User lastTest = userService.getUser("KAMAZ", 2115);
        if (lastTest != null) {
            System.out.println(lastTest.getFirstName() + " has a " + lastTest.getCar().getModel());
        } else {
            System.out.println(" no data in the database " );
        }

        User lastTest2 = userService.getUser("BMW", 9999);
        if (lastTest2 != null) {
            System.out.println(lastTest2.getFirstName() + " has a " + lastTest2.getCar().getModel());
        } else {
            System.out.println(" no data in the database " );
        }


        context.close();
    }
}
