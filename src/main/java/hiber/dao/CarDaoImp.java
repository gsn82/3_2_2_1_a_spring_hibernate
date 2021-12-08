package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> listUsers() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().
                createQuery("select car from Car car",Car.class);
        return query.getResultList();
    }

    @Override
    public User getUser(String model, int series) {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("select car.user from Car car where car.model= :model and car.series= :series",User.class)
                .setParameter("model", model)
                .setParameter("series", series);

        // условие добавил, если
        if (query.getResultList().size() > 0) {
            return query.getResultList().get(0);
        } else {
            return null;
        }
    }
}
