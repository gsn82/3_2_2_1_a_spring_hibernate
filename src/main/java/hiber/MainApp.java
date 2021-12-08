package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        // создаем user

        User user1 = new User("Ivan", "Ivanov", "user1@gmail.com");
        Car car1 = new Car("Niva", 7777, user1);

        User user2 = new User("Alex", "Petrov", "user2@gmail.com");
        Car car2 = new Car("UAZ", 5555, user2);

        User user3 = new User("Sergey", "Sidorov", "user3@gmail.com");

        Car car3 = new Car("Fiat", 8888, user3);

        User user4 = new User("Fedor", "Petrosan", "user4@gmail.com");
        Car car4 = new Car("BMW", 9999,user4);/**/

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        CarService carService = context.getBean(CarService.class);
        // добавляем user
        carService.add(car1);
        carService.add(car2);
        carService.add(car3);
        carService.add(car4);


        // выводим на экран
        List<Car> cars = carService.listCars();
        for (Car car : cars) {
            System.out.println("Id = " + car.getId());
            System.out.println("Model = " + car.getModel());
            System.out.println("Series = " + car.getSeries());

            User user = carService.getUser(car.getModel(),car.getSeries());

            System.out.println("FirstName = " + user.getFirstName());
            System.out.println("LastName() = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }


        User lastTest = carService.getUser("KAMAZ", 2115);
        if (lastTest != null) {
            System.out.println(lastTest.getFirstName() + " has a " +"KAMAZ");
        } else {
            System.out.println(" no data in the database ");
        }

        User lastTest2 = carService.getUser("BMW2", 9999);
        if (lastTest2 != null) {
            System.out.println(lastTest2.getFirstName() + " has a " +"BMW2");
        } else {
            System.out.println(" no data in the database ");
        }


        User lastTest3 = carService.getUser(car4.getModel(), car4.getSeries());
        if (lastTest3 != null) {
            System.out.println(lastTest3.getFirstName() + " has a " + car4.getModel());
        } else {
            System.out.println(" no data in the database ");
        }


        context.close();/**/
    }
}
