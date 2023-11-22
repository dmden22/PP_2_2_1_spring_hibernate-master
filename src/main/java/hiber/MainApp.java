package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Model1", 1);
        Car car2 = new Car("Model2", 2);
        Car car3 = new Car("Model3", 3);
        Car car4 = new Car("Model4", 4);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        userService.add(car1);
        userService.add(car2);
        userService.add(car3);
        userService.add(car4);

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        String searchModel = "Model2";
        int searchSeries = 2;
        System.out.println("Ищем владельца автомобиля Модель: " + searchModel + " Серия: " + searchSeries + " :");
        User userByModelAndSeries = userService.getUserWithCar(searchModel, searchSeries);
        if (userByModelAndSeries != null) {
            System.out.println("Id = " + userByModelAndSeries.getId());
            System.out.println("First Name = " + userByModelAndSeries.getFirstName());
            System.out.println("Last Name = " + userByModelAndSeries.getLastName());
            System.out.println("Email = " + userByModelAndSeries.getEmail());
            System.out.println("Model = " + userByModelAndSeries.getCar().getModel());
            System.out.println("Series = " + userByModelAndSeries.getCar().getSeries());
            System.out.println();
        } else {
            System.out.println("Пользователя с такой моделью и серией НЕ НАЙДЕНО");
        }

        context.close();
    }
}
